/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import {Card, Form, Input, Button, Modal, message, Table, Divider, Tree, Spin, Row, Col, Select, Icon} from 'antd';
import { checkTypeWithEnglishAndNumbers } from '../../../helpers/validator'
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './AdminList.less';
import moment from "moment";
import PaginationHelper from "../../../helpers/PaginationHelper";

const FormItem = Form.Item;
const { TreeNode } = Tree;
const status = ['未知', '正常', '禁用'];

// 列表
function List ({ dataSource, pagination, searchParams, dispatch, handleModalVisible }) {
  const columns = [
    {
      title: '用户名',
      dataIndex: 'username'
    },
    {
      title: '昵称',
      dataIndex: 'nickname',
    },
    {
      title: '状态',
      dataIndex: 'status',
      render(val) {
        return <span>{status[val]}</span>; // TODO 芋艿，此处要改
      },
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      render: val => <span>{moment(val).format('YYYY-MM-DD HH:mm')}</span>,
    },
    {
      title: '操作',
      width: 360,
      render: (text, record) => {
        const statusText = record.status === 1 ? '禁用' : '禁用';
        return (
          <Fragment>
            <a onClick={() => handleModalVisible(true, 'update', record)}>编辑</a>
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

  function onPageChange(page) {
    dispatch({
      type: 'adminList/query',
      payload: {
        pageNo: page.current,
        pageSize: page.pageSize,
        ...searchParams
      }
    })
  };

  return (
    <Table
      columns={columns}
      dataSource={dataSource}
      rowKey="id"
      // pagination={{
      //   current: pageNo,
      //   pageSize: pageSize,
      //   total: count,
      //   onChange: this.onPageChange
      // }}
      pagination={pagination}
      onChange={onPageChange}
    />
  )
}

// 搜索表单
// TODO 芋艿，有没办法换成上面那种写法
const SearchForm = Form.create()(props => {
  const {
    form,
    form: { getFieldDecorator },
    dispatch
  } = props;

  function search() {
    dispatch({
      type: 'adminList/query',
      payload: {
        ...PaginationHelper.defaultPayload,
        ...form.getFieldsValue()
      }
    })
  }

  // 提交搜索
  function handleSubmit(e) {
    // 阻止默认事件
    e.preventDefault();
    // 提交搜索
    search();
  }

  // 重置搜索
  function handleReset() {
    // 重置表单
    form.resetFields();
    // 执行搜索
    search();
  }

  return (
    <Form onSubmit={handleSubmit} layout="inline">
      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <FormItem label="昵称">
            {getFieldDecorator('nickname')(<Input placeholder="请输入" />)}
          </FormItem>
        </Col>
        <Col md={8} sm={24}>
            <span className={styles.submitButtons}>
              <Button type="primary" htmlType="submit">
                查询
              </Button>
              <Button style={{ marginLeft: 8 }} onClick={handleReset}>
                重置
              </Button>
            </span>
        </Col>
      </Row>
    </Form>
  );
});

// 添加 or 修改 Form 表单
const AddOrUpdateForm = Form.create()(props => {
  const { dispatch, modalVisible, form, handleModalVisible, modalType, formVals } = props;

  const okHandle = () => {
    form.validateFields((err, fields) => {
      if (err) return;
      // 添加表单
      if (modalType === 'add') {
        dispatch({
          type: 'adminList/add',
          payload: {
            body: {
              ...fields,
            },
            callback: () => {
              // 清空表单
              form.resetFields();
              // 提示
              message.success('添加成功');
              // 关闭弹窗
              handleModalVisible();
            },
          },
        });
        // 修改表单
      } else {
        dispatch({
          type: 'adminList/update',
          payload: {
            body: {
              ...formVals,
              ...fields,
            },
            callback: () => {
              // 清空表单
              form.resetFields();
              // 提示
              message.success('更新成功');
              // 关闭弹窗
              handleModalVisible();
            },
          },
        });
      }
    });
  };

  const title = modalType === 'add' ? '新建管理员' : '更新管理员';
  return (
    <Modal
      destroyOnClose
      title={title}
      visible={modalVisible}
      onOk={okHandle}
      okText='保存'
      onCancel={() => handleModalVisible()}
    >
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="用户名">
        {form.getFieldDecorator('username', {
          rules: [{ required: true, message: '请输入用户名！'},
            {max: 16, min:6, message: '长度为 6-16 位'},
            { validator: (rule, value, callback) => checkTypeWithEnglishAndNumbers(rule, value, callback, '数字以及字母')}
          ],
          initialValue: formVals.username,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="昵称">
        {form.getFieldDecorator('nickname', {
          rules: [{ required: true, message: '请输入昵称！'},
            {max: 10, message: '姓名最大长度为 10'}],
          initialValue: formVals.nickname,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="密码">
        {form.getFieldDecorator('password', {
          rules: [{ required: modalType === 'add', message: '请填写密码'}, // 添加时，必须输入密码
            {max: 16, min: 6, message: '长度为 6-18 位'}],
          initialValue: formVals.password,
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
  // list: adminList.list,
  // pagination: adminList.pagination,
  ...adminList,
  data: adminList,
  loading: loading.models.resourceList,
}))

@Form.create()
class AdminList extends PureComponent {
  state = {

    // 分配角色弹窗
    modalRoleVisible: false,
    modalRoleRow: {},
  };

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'adminList/query',
      payload: {
        ...PaginationHelper.defaultPayload
      },
    });
  }

  handleModalVisible = (modalVisible, modalType, formVals) => {
    // debugger;
    const { dispatch } = this.props;
    dispatch({
      type: 'adminList/setAll',
      payload: {
        modalVisible,
        modalType,
        formVals: formVals || {}
      },
    });
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
    // let that = this;
    const { dispatch, list, searchParams, pagination, modalVisible, data, modalType, formVals } = this.props;
    const { roleList, roleCheckedKeys, roleAssignLoading } = data;
    // const {
    //   // modalVisible,
    //   // modalType,
    //   formVals,
    //   modalRoleVisible,
    // } = this.state;
    const modalRoleVisible = false;


    // 列表属性
    const listProps = {
      dataSource: list,
      pagination,
      searchParams,
      dispatch,
      handleModalVisible: this.handleModalVisible, // Function
    };

    // 搜索表单属性
    const searchFormProps = {
      dispatch,
    };

    // 添加
    const addFormProps = {
      modalVisible,
      modalType: modalType,
      formVals: formVals,
      dispatch,
      handleModalVisible: this.handleModalVisible, // Function
    };

    return (
      <PageHeaderWrapper>
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <SearchForm {...searchFormProps} />
            </div>
            <div className={styles.tableListOperator}>
              <Button
                icon="plus"
                type="primary"
                onClick={() => this.handleModalVisible(true, 'add', {})}
              >
                新建管理员
              </Button>
            </div>
          </div>
          <List {...listProps} />

        </Card>
        <AddOrUpdateForm {...addFormProps} />

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

export default AdminList;
