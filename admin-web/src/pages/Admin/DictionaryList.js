/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import { Card, Form, Input, Select, Button, Modal, message, Table, Divider } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './DictionaryList.less';

const FormItem = Form.Item;
const { Option } = Select;
const types = ['未知', '菜单', '链接'];

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

  const title = modalType === 'add' ? '添加一个数据字典' : '更新一个数据字典';
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
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="菜单展示名">
        {form.getFieldDecorator('displayName', {
          rules: [{ required: true, message: '请输入菜单展示名字！', min: 2 }],
          initialValue: initValues.displayName,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="操作">
        {form.getFieldDecorator('handler', {
          initialValue: initValues.handler,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="资源名字">
        {form.getFieldDecorator('name', {
          rules: [{ required: true, message: '请输入资源名字！' }],
          initialValue: initValues.name,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="父级资源编号">
        {form.getFieldDecorator('pid', {
          rules: [{ required: true, message: '请输入父级编号！' }],
          initialValue: initValues.pid,
        })(<Input placeholder="请输入" />)}
        <span>根节点为 0</span>
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="排序">
        {form.getFieldDecorator('sort', {
          rules: [{ required: true, message: '请输入菜单排序！' }],
          initialValue: initValues.sort,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="资源类型">
        {form.getFieldDecorator('type', {
          rules: [{ required: true, message: '请选择资源类型！' }],
          initialValue: 1,
        })(
          <Select showSearch style={selectStyle} placeholder="请选择">
            <Option value={1}>菜单</Option>
            <Option value={2}>Url</Option>
          </Select>
        )}
      </FormItem>
    </Modal>
  );
});

@connect(({ dictionaryList, loading }) => ({
  dictionaryList,
  list: dictionaryList.list,
  loading: loading.models.dictionaryList,
}))

@Form.create()
class DictionaryList extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add update
    initValues: {},
  };

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'dictionaryList/tree',
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
    const { dispatch } = this.props;
    if (modalType === 'add') {
      dispatch({
        type: 'dictionaryList/add',
        payload: {
          body: {
            ...fields,
          },
          callback: () => {
            message.success('添加成功');
            this.handleModalVisible();
          },
        },
      });
    } else {
      dispatch({
        type: 'dictionaryList/update',
        payload: {
          body: {
            ...initValues,
            ...fields,
          },
          callback: () => {
            message.success('更新成功');
            this.handleModalVisible();
          },
        },
      });
    }
  };

  handleDelete(row) {
    const { dispatch } = this.props;
    Modal.confirm({
      title: `确认删除?`,
      content: `${row.displayName}`,
      onOk() {
        dispatch({
          type: 'dictionaryList/delete',
          payload: {
            id: row.id,
          },
        });
      },
      onCancel() {},
    });
  }

  render() {
    const { list } = this.props;
    const { modalVisible, modalType, initValues } = this.state;
    const parentMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleModalVisible,
      modalType,
      initValues,
    };

    const columns = [
      {
        title: '枚举值',
        dataIndex: 'enumValue'
      },
      {
        title: '编号',
        dataIndex: 'id',
      },
      {
        title: '小类数值',
        dataIndex: 'value'
      },
      {
        title: '展示名',
        dataIndex: 'displayName'
      },
      {
        title: '排序值',
        dataIndex: 'sort'
      },
      {
        title: '备注',
        dataIndex: 'memo'
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

    return (
      <PageHeaderWrapper>
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
          <Table defaultExpandAllRows={true} columns={columns} dataSource={list} rowKey="enumValue" />
        </Card>
        <CreateForm {...parentMethods} modalVisible={modalVisible} />
      </PageHeaderWrapper>
    );
  }
}

export default DictionaryList;
