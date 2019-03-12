/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import { Card, Form, Input, Button, Modal, message, Table, Divider, Tree, Spin } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './AdminList.less';

const FormItem = Form.Item;
const { TreeNode } = Tree;
const status = ['未知', '正常', '禁用'];

// 添加 form 表单
const CreateForm = Form.create()(props => {
  const { modalVisible, form, handleAdd, handleModalVisible, modalType, initValues } = props;

  const okHandle = () => {
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      handleAdd({
        fields: fieldsValue,
        modalType,
        initValues,
      });
    });
  };

  const selectStyle = {
    width: 200,
  };

  const title = modalType === 'add' ? '添加一个 Resource' : '更新一个 Resource';
  const okText = modalType === 'add' ? '添加' : '更新';
  return (
    <Modal
      destroyOnClose
      title={title}
      visible={modalVisible}
      onOk={okHandle}
      okText={okText}
      onCancel={() => handleModalVisible()}
    >
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="名称">
        {form.getFieldDecorator('username', {
          rules: [{ required: true, message: '请输入名称！', min: 2 }],
          initialValue: initValues.username,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="昵称">
        {form.getFieldDecorator('nickname', {
          rules: [{ required: true, message: '请输入昵称！', min: 2 }],
          initialValue: initValues.nickname,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="密码">
        {form.getFieldDecorator('password', {
          initialValue: initValues.password,
        })(<Input placeholder="请输入" type="password" />)}
      </FormItem>
    </Modal>
  );
});

// 角色分配
const RoleAssignModal = Form.create()(props => {
  const {
    modalVisible,
    form,
    handleOk,
    handleModalVisible,
    treeData,
    checkedKeys,
    loading,
    handleCheckBoxClick,
  } = props;

  const renderTreeNodes = data => {
    return data.map(item => {
      if (item.children) {
        return (
          <TreeNode title={item.title} key={item.key} dataRef={item}>
            {renderTreeNodes(item.children)}
          </TreeNode>
        );
      }
      return <TreeNode title={item.title} key={item.key} dataRef={item} />;
    });
  };

  const renderModalContent = treeData => {
    const RenderTreeNodes = renderTreeNodes(treeData);
    if (RenderTreeNodes) {
      return (
        <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="角色">
          {form.getFieldDecorator('name', {})(
            <Tree
              defaultExpandAll={true}
              checkable={true}
              multiple={true}
              checkedKeys={checkedKeys}
              onCheck={handleCheckBoxClick}
            >
              {renderTreeNodes(treeData)}
            </Tree>
          )}
        </FormItem>
      );
    } else {
      return null;
    }
  };

  const okHandle = () => {
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      handleOk({
        fields: fieldsValue,
      });
    });
  };

  return (
    <Modal
      destroyOnClose
      title="分配角色"
      visible={modalVisible}
      onOk={okHandle}
      onCancel={() => handleModalVisible()}
    >
      <Spin spinning={loading}>{renderModalContent(treeData)}</Spin>
    </Modal>
  );
});

@connect(({ adminList, loading }) => ({
  list: adminList.list,
  data: adminList,
  loading: loading.models.resourceList,
}))
@Form.create()
class ResourceList extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add update
    initValues: {},

    modalRoleVisible: false,
    modalRoleRow: {},
  };

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'adminList/query',
      payload: {},
    });
  }

  handleModalVisible = (flag, modalType, initValues) => {
    this.setState({
      modalVisible: !!flag,
      initValues: initValues || {},
      modalType: modalType || 'add',
    });
  };

  handleAdd = ({ fields, modalType, initValues }) => {
    const { dispatch, data } = this.props;
    const queryParams = {
      pageNo: data.pageNo,
      pageSize: data.pageSize,
    };
    if (modalType === 'add') {
      dispatch({
        type: 'adminList/add',
        payload: {
          body: {
            ...fields,
          },
          queryParams,
          callback: () => {
            message.success('添加成功');
            this.handleModalVisible();
          },
        },
      });
    } else {
      dispatch({
        type: 'adminList/update',
        payload: {
          body: {
            ...initValues,
            ...fields,
          },
          queryParams,
          callback: () => {
            message.success('更新成功');
            this.handleModalVisible();
          },
        },
      });
    }
  };

  handleStatus(row) {
    const { dispatch, data } = this.props;
    const queryParams = {
      pageNo: data.pageNo,
      pageSize: data.pageSize,
    };

    const title = row.status === 1 ? '确认禁用' : '取消禁用';
    const updateStatus = row.status === 1 ? 2 : 1;

    Modal.confirm({
      title: `${title}?`,
      content: `${row.username}`,
      onOk() {
        dispatch({
          type: 'adminList/updateStatus',
          payload: {
            body: {
              id: row.id,
              status: updateStatus,
            },
            queryParams,
          },
        });
      },
      onCancel() {},
    });
  }

  handleDelete(row) {
    const { dispatch, data } = this.props;
    const queryParams = {
      pageNo: data.pageNo,
      pageSize: data.pageSize,
    };

    Modal.confirm({
      title: `确认删除?`,
      content: `${row.username}`,
      onOk() {
        dispatch({
          type: 'adminList/delete',
          payload: {
            body: {
              id: row.id,
            },
            queryParams,
          },
        });
      },
      onCancel() {},
    });
  }

  handleRoleAssign = row => {
    const { dispatch } = this.props;
    this.setState({
      modalRoleVisible: !!true,
      modalRoleRow: row,
    });

    dispatch({
      type: 'adminList/queryRoleList',
      payload: {
        id: row.id,
      },
    });
  };

  handleRoleAssignCheckBoxClick = checkedKeys => {
    const { dispatch } = this.props;
    const newCheckedKeys = checkedKeys.map(item => {
      return parseInt(item);
    });
    dispatch({
      type: 'adminList/changeRoleCheckedKeys',
      payload: newCheckedKeys,
    });
  };

  handleRoleAssignOK = () => {
    const { dispatch, data } = this.props;
    const { modalRoleRow } = this.state;
    dispatch({
      type: 'adminList/roleAssign',
      payload: {
        id: modalRoleRow.id,
        roleIds: data.roleCheckedKeys,
      },
    });
    this.handleRoleAssignModalVisibleClose(false);
  };

  handleRoleAssignModalVisibleClose = fag => {
    this.setState({
      modalRoleVisible: !!fag,
    });
  };

  render() {
    const { list, data } = this.props;
    const { roleList, roleCheckedKeys, roleAssignLoading } = data;
    const {
      modalVisible,
      modalType,
      initValues,
      defaultExpandAllRows,
      modalRoleVisible,
    } = this.state;

    const parentMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleModalVisible,
      modalType,
      initValues,
    };

    const columns = [
      {
        title: 'id',
        dataIndex: 'id',
        render: text => <strong>{text}</strong>,
      },
      {
        title: '用户名',
        dataIndex: 'username',
        render: text => <a>{text}</a>,
      },
      {
        title: '昵称',
        dataIndex: 'nickname',
      },
      {
        title: '状态',
        dataIndex: 'status',
        render(val) {
          return <span>{status[val]}</span>;
        },
      },
      {
        title: '操作',
        width: 300,
        render: (text, record) => {
          const statusText = record.status === 1 ? '确认禁用' : '取消禁用';
          return (
            <Fragment>
              <a onClick={() => this.handleModalVisible(true, 'update', record)}>更新</a>
              <Divider type="vertical" />
              <a onClick={() => this.handleRoleAssign(record)}>角色分配</a>
              <Divider type="vertical" />
              <a className={styles.tableDelete} onClick={() => this.handleStatus(record)}>
                {statusText}
              </a>
              <Divider type="vertical" />
              <a className={styles.tableDelete} onClick={() => this.handleDelete(record)}>
                删除
              </a>
            </Fragment>
          );
        },
      },
    ];

    return (
      <PageHeaderWrapper title="查询表格">
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListOperator}>
              <Button
                icon="plus"
                type="primary"
                onClick={() => this.handleModalVisible(true, 'add', {})}
              >
                新建
              </Button>
            </div>
          </div>
          <Table
            defaultExpandAllRows={defaultExpandAllRows}
            columns={columns}
            dataSource={list}
            rowKey="id"
          />
        </Card>
        <CreateForm {...parentMethods} modalVisible={modalVisible} />

        <RoleAssignModal
          loading={roleAssignLoading}
          treeData={roleList}
          checkedKeys={roleCheckedKeys}
          handleOk={this.handleRoleAssignOK}
          modalVisible={modalRoleVisible}
          handleCheckBoxClick={this.handleRoleAssignCheckBoxClick}
          handleModalVisible={() => this.handleRoleAssignModalVisibleClose(false)}
        />
      </PageHeaderWrapper>
    );
  }
}

export default ResourceList;
