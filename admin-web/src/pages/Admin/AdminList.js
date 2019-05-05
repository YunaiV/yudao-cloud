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
function List ({ dataSource, loading, pagination, searchParams, dispatch,
                 handleModalVisible, handleRoleAssignModalVisible}) {

  function handleRoleAssign(record) {
    // 显示 Modal
    handleRoleAssignModalVisible(true, record);
    // 查询角色列表
    dispatch({
      type: 'adminList/queryRoleList',
      payload: {
        id: record.id,
      },
    });
  }

  function handleStatus(record) {
    Modal.confirm({
      title: record.status === 1 ? '确认禁用' : '取消禁用',
      content: `${record.username}`,
      onOk() {
        dispatch({
          type: 'adminList/updateStatus',
          payload: {
            id: record.id,
            status: record.status === 1 ? 2 : 1,
          },
        });
      },
      onCancel() {},
    });
  }

  function handleDelete(record) {
    Modal.confirm({
      title: `确认删除?`,
      content: `${record.username}`,
      onOk() {
        dispatch({
          type: 'adminList/delete',
          payload: {
            id: record.id,
          },
        });
      },
      onCancel() {},
    });
  }

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
        const statusText = record.status === 1 ? '禁用' : '开启'; // TODO 芋艿，此处要改
        return (
          <Fragment>
            <a onClick={() => handleModalVisible(true, 'update', record)}>编辑</a>
            <Divider type="vertical" />
            <a onClick={() => handleRoleAssign(record)}>角色分配</a>
            <Divider type="vertical" />
            <a className={styles.tableDelete} onClick={() => handleStatus(record)}>
              {statusText}
            </a>
            {
              record.status === 2 ?
                <span>
                  <Divider type="vertical" />
                  <a className={styles.tableDelete} onClick={() => handleDelete(record)}>
                    删除
                  </a>
                </span> : null
            }
          </Fragment>
        );
      },
    },
  ];

  function onPageChange(page) { // 翻页
    dispatch({
      type: 'adminList/query',
      payload: {
        pageNo: page.current,
        pageSize: page.pageSize,
        ...searchParams
      }
    })
  }

  return (
    <Table
      columns={columns}
      dataSource={dataSource}
      loading={loading}
      rowKey="id"
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
              message.success('新建成功');
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
              id: formVals.id,
              ...fields,
            },
            callback: () => {
              // 清空表单
              form.resetFields();
              // 提示
              message.success('编辑成功');
              // 关闭弹窗
              handleModalVisible();
            },
          },
        });
      }
    });
  };

  const title = modalType === 'add' ? '新建员工' : '更新员工';
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

// 角色分配 Modal
const RoleAssignModal = Form.create()(props => {
  const {
    modalVisible,
    form,
    handleModalVisible,
    treeData,
    checkedKeys,
    loading,
    formVals,
    dispatch,
  } = props;

  const handleCheckBoxClick = checkedKeys => {
    // 获得新选择
    const newCheckedKeys = checkedKeys.map(item => {
      return parseInt(item);
    });
    // 设置到 model 中
    dispatch({
      type: 'adminList/changeRoleCheckedKeys',
      payload: newCheckedKeys,
    });
  };

  const renderTreeNodes = data => {
    return data.map(item => {
      if (item.children) { // 递归拼接节点
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
      // debugger;
      dispatch({
        type: 'adminList/roleAssign',
        payload: {
          body: {
            id: formVals.id,
            roleIds: checkedKeys,
          },
          callback: () => {
            // 清空表单
            form.resetFields();
            // 提示
            message.success('分配角色成功');
            // 关闭弹窗
            handleModalVisible(false);
          },
        },
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
      <Spin spinning={loading}>
        {renderModalContent(treeData)}
      </Spin>
    </Modal>
  );
});


@connect(({ adminList }) => ({
  // list: adminList.list,
  // pagination: adminList.pagination,
  ...adminList,
}))

// 主界面
@Form.create()
class AdminList extends PureComponent {

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'adminList/query',
      payload: {
        ...PaginationHelper.defaultPayload
      },
    });
  }

  handleModalVisible = (modalVisible, modalType, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'adminList/setAll',
      payload: {
        modalVisible,
        modalType,
        formVals: record || {}
      },
    });
  };

  handleRoleAssignModalVisible = (roleModalVisible, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'adminList/setAll',
      payload: {
        roleModalVisible: roleModalVisible,
        formVals: record || {}
      },
    });
  };

  render() {
    // let that = this;
    const { dispatch,
      list, listLoading, searchParams, pagination,
      modalVisible, modalType, formVals,
      confirmLoading,
      roleList, roleModalVisible, roleAssignLoading, roleCheckedKeys  } = this.props;

    // 列表属性
    const listProps = {
      dataSource: list,
      pagination,
      searchParams,
      dispatch,
      loading: listLoading,
      confirmLoading,
      handleModalVisible: this.handleModalVisible, // Function
      handleRoleAssignModalVisible: this.handleRoleAssignModalVisible, // Function
    };

    // 搜索表单属性
    const searchFormProps = {
      dispatch,
    };

    // 添加 or 更新表单属性
    const addOrUpdateFormProps = {
      modalVisible,
      modalType,
      formVals,
      dispatch,
      handleModalVisible: this.handleModalVisible, // Function
    };

    // 分配角色 Modal 属性
    const roleAssignModal = {
      loading: roleAssignLoading,
      treeData: roleList,
      formVals,
      checkedKeys: roleCheckedKeys,
      modalVisible: roleModalVisible,
      dispatch,
      handleModalVisible: this.handleRoleAssignModalVisible, // Function
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
                新建员工
              </Button>
            </div>
          </div>
          <List {...listProps} />
        </Card>

        <AddOrUpdateForm {...addOrUpdateFormProps} />

        <RoleAssignModal {...roleAssignModal} />
      </PageHeaderWrapper>
    );
  }
}

export default AdminList;
