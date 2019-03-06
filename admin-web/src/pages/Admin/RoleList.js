/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import { Card, Form, Input, Select, Button, Modal, message, Table, Divider } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './RoleList.less';

const FormItem = Form.Item;
const { Option } = Select;

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

  const title = modalType === 'add' ? '添加一个 Role' : '更新一个 Role';
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
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="角色名">
        {form.getFieldDecorator('name', {
          rules: [{ required: true, message: '请输入角色名！', min: 2 }],
          initialValue: initValues.name,
        })(<Input placeholder="请输入" />)}
      </FormItem>
    </Modal>
  );
});

@connect(({ roleList, loading }) => ({
  roleList,
  list: roleList.list,
  data: roleList,
  loading: loading.models.resourceList,
}))
@Form.create()
class RoleList extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add update
    initValues: {},
  };

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'roleList/query',
      payload: {
        name: '',
        pageNo: 0,
        pageSize: 10,
      },
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
        type: 'roleList/add',
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
        type: 'roleList/update',
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

  handleDelete(row) {
    const { dispatch, data } = this.props;
    const queryParams = {
      pageNo: data.pageNo,
      pageSize: data.pageSize,
    };
    Modal.confirm({
      title: `确认删除?`,
      content: `${row.name}`,
      onOk() {
        dispatch({
          type: 'roleList/delete',
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

  render() {
    const { list, data } = this.props;
    const { modalVisible, modalType, initValues, defaultExpandAllRows } = this.state;
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
        title: '名称',
        dataIndex: 'name',
      },
      {
        title: '创建时间',
        dataIndex: 'createTime',
        sorter: true,
        render: val => <span>{moment(val).format('YYYY-MM-DD')}</span>,
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            <a onClick={() => this.handleModalVisible(true, 'update', record)}>更新</a>
            <Divider type="vertical" />
            <a className={styles.tableDelete} onClick={() => this.handleDelete(record)}>
              删除
            </a>
          </Fragment>
        ),
      },
    ];

    const paginationProps = {
      current: data.pageNo,
      pageSize: data.pageSize,
      total: data.count,
    };
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
          <Table columns={columns} dataSource={list} rowKey="id" />
        </Card>
        <CreateForm {...parentMethods} modalVisible={modalVisible} />
      </PageHeaderWrapper>
    );
  }
}

export default RoleList;
