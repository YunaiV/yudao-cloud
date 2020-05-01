/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import {
  Card,
  Form,
  Input,
  Button,
  Modal,
  message,
  Table,
  Divider,
  Tree,
  Spin,
  Row,
  Col,
  Select,
  Icon,
  TreeSelect,
} from 'antd';
import { checkTypeWithEnglishAndNumbers } from '../../../helpers/validator';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './AdminList.less';
import moment from 'moment';
import PaginationHelper from '../../../helpers/PaginationHelper';

const FormItem = Form.Item;
const { TreeNode } = Tree;
const status = ['未知', '在职', '离职'];

// 列表
function List({
  dataSource,
  loading,
  pagination,
  searchParams,
  dispatch,
  handleModalVisible,
  handleRoleAssignModalVisible,
}) {
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
      title: '账号',
      dataIndex: 'username',
    },
    {
      title: '员工姓名',
      dataIndex: 'name',
    },
    {
      title: '部门',
      dataIndex: 'deptment.name',
    },
    {
      title: '角色',
      dataIndex: 'roles',
      render(roles) {
        let text = '';
        if (roles) {
          for (let i in roles) {
            if (i > 0) {
              text += '  ';
            }
            text += roles[i].name;
          }
        }
        return <span>{text}</span>;
      },
    },
    {
      title: '在职状态',
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
        return (
          <Fragment>
            <a onClick={() => handleModalVisible(true, 'update', record)}>编辑</a>
            <Divider type="vertical" />
            <a onClick={() => handleRoleAssign(record)}>角色分配</a>
            <Divider type="vertical" />
            {record.status === 2 ? (
              <span>
                <Divider type="vertical" />
                <a className={styles.tableDelete} onClick={() => handleDelete(record)}>
                  删除
                </a>
              </span>
            ) : null}
          </Fragment>
        );
      },
    },
  ];

  function onPageChange(page) {
    // 翻页
    dispatch({
      type: 'adminList/query',
      payload: {
        pageNo: page.current,
        pageSize: page.pageSize,
        ...searchParams,
      },
    });
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
  );
}

// 搜索表单
const SearchForm = Form.create()(props => {
  const {
    form,
    form: { getFieldDecorator },
    dispatch,
    deptSelectTree,
  } = props;

  function search() {
    const fields = form.getFieldsValue();
    if (fields.deptmentId) {
      const deptmentId = fields.deptmentId.split('-')[1];
      fields.deptmentId = deptmentId;
    }
    dispatch({
      type: 'adminList/query',
      payload: {
        ...PaginationHelper.defaultPayload,
        ...fields,
      },
    });
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
        <Col md={6} sm={24}>
          <FormItem label="员工姓名">
            {getFieldDecorator('name')(<Input style={{ width: 200 }} placeholder="请输入" />)}
          </FormItem>
        </Col>
        <Col md={6} sm={24}>
          <FormItem label="归属部门">
            {getFieldDecorator('deptmentId', {
              rules: [{ required: true, message: '请选择部门' }],
            })(
              <TreeSelect
                showSearch
                style={{ width: 200 }}
                dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
                treeData={deptSelectTree}
                placeholder="选择部门"
              />
            )}
          </FormItem>
        </Col>

        <Col md={12} sm={24}>
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
  const {
    dispatch,
    modalVisible,
    form,
    handleModalVisible,
    modalType,
    formVals,
    deptSelectTree,
  } = props;

  const okHandle = () => {
    form.validateFields((err, fields) => {
      if (err) return;
      // 添加表单
      if (fields.deptmentId) {
        const deptmentId = fields.deptmentId.split('-')[1];
        fields.deptmentId = deptmentId;
      }
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
      okText="保存"
      onCancel={() => handleModalVisible()}
    >
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="员工姓名">
        {form.getFieldDecorator('name', {
          rules: [
            { required: true, message: '请输入员工姓名！' },
            { max: 10, message: '姓名最大长度为 10' },
          ],
          initialValue: formVals.name,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="归属部门">
        {form.getFieldDecorator('deptmentId', {
          rules: [{ required: true, message: '请选择部门' }],
          initialValue:
            formVals.deptmentId && formVals.deptmentId !== 0 ? formVals.deptmentId : null,
        })(
          <TreeSelect
            showSearch
            style={{ width: 280 }}
            dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
            treeData={deptSelectTree}
            placeholder="选择部门"
          />
        )}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="账号">
        {form.getFieldDecorator('username', {
          rules: [
            { required: true, message: '请输入账号！' },
            { max: 16, min: 6, message: '长度为 6-16 位' },
            {
              validator: (rule, value, callback) =>
                checkTypeWithEnglishAndNumbers(rule, value, callback, '数字以及字母'),
            },
          ],
          initialValue: formVals.username,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="密码">
        {form.getFieldDecorator('password', {
          rules: [
            { required: modalType === 'add', message: '请填写密码' }, // 添加时，必须输入密码
            { max: 16, min: 6, message: '长度为 6-18 位' },
          ],
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
      if (item.children) {
        // 递归拼接节点
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
      <Spin spinning={loading}>{renderModalContent(treeData)}</Spin>
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
        ...PaginationHelper.defaultPayload,
      },
    });
    dispatch({
      type: 'adminList/getDeptmentTree',
      payload: {},
    });
  }

  handleModalVisible = (modalVisible, modalType, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'adminList/setAll',
      payload: {
        modalVisible,
        modalType,
        formVals: record || {},
      },
    });
  };

  handleRoleAssignModalVisible = (roleModalVisible, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'adminList/setAll',
      payload: {
        roleModalVisible: roleModalVisible,
        formVals: record || {},
      },
    });
  };

  render() {
    // let that = this;
    const {
      dispatch,
      list,
      listLoading,
      searchParams,
      pagination,
      modalVisible,
      modalType,
      formVals,
      confirmLoading,
      roleList,
      roleModalVisible,
      roleAssignLoading,
      roleCheckedKeys,
      deptSelectTree,
    } = this.props;

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
      deptSelectTree,
    };

    // 添加 or 更新表单属性
    const addOrUpdateFormProps = {
      modalVisible,
      modalType,
      formVals,
      dispatch,
      deptSelectTree,
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
