/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import {Card, Form, Input, Select, Button, Modal, message, Table, Divider, InputNumber} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './ProductCategoryList.less';

const FormItem = Form.Item;
const { Option } = Select;
const status = ['未知', '开启', '禁用'];

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
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="分类名">
        {form.getFieldDecorator('name', {
          rules: [{ required: true, message: '请输入分类名！', min: 2 }],
          initialValue: initValues.name,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="分类图片">
        {form.getFieldDecorator('picUrl', {
          initialValue: initValues.handler,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="父分类编号">
        {form.getFieldDecorator('pid', {
          rules: [{ required: true, message: '请输入父分类编号！' }],
          initialValue: initValues.pid,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="排序值">
        {form.getFieldDecorator('sort', {
          rules: [{ required: true, message: '请输入排序值！' }],
          initialValue: initValues.sort,
        })(<InputNumber placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="描述">
        {form.getFieldDecorator('description', {
          rules: [{ required: true, message: '请输入描述！' }],
          initialValue: initValues.description,
        })(<Input.TextArea placeholder="请输入" />)}
      </FormItem>
    </Modal>
  );
});

@connect(({ productCategoryList, loading }) => ({
  productCategoryList,
  list: productCategoryList.list,
  loading: loading.models.productCategoryList,
}))
@Form.create()
class ProductCategoryList extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add update
    initValues: {},
  };

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'productCategoryList/tree',
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
        type: 'productCategoryList/add',
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
        type: 'productCategoryList/update',
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

  handleStatus(row) {
    const { dispatch, data } = this.props;

    const title = row.status === 1 ? '确认禁用？' : '确认开启？';
    const updateStatus = row.status === 1 ? 2 : 1;

    Modal.confirm({
      title: `${title}`,
      content: `${row.name}`,
      onOk() {
        dispatch({
          type: 'productCategoryList/updateStatus',
          payload: {
            body: {
              id: row.id,
              status: updateStatus,
            }
          },
        });
      },
      onCancel() {},
    });
  }

  handleDelete(row) {
    const { dispatch } = this.props;
    Modal.confirm({
      title: `确认删除?`,
      content: `${row.displayName}`,
      onOk() {
        dispatch({
          type: 'productCategoryList/delete',
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
    const that = this;

    const columns = [
      {
        title: 'id',
        dataIndex: 'id',
        render: text => <strong>{text}</strong>,
      },
      {
        title: '分类名',
        dataIndex: 'name',
      },
      {
        title: '分类图片',
        dataIndex: 'picUrl',
        render(val) {
          return <img width={120} src={val} />;
        },
      },
      {
        title: '排序值',
        dataIndex: 'sort',
      },
      {
        title: '创建时间',
        dataIndex: 'createTime',
        render: val => <span>{moment(val).format('YYYY-MM-DD')}</span>,
      },
      {
        title: '状态',
        dataIndex: 'status',
        render(val) {
          return <span>{status[val]}</span>;
        },
      },
      {
        title: '描述',
        dataIndex: 'description',
      },
      {
        title: '操作',
        render: (text, record) => {
          const statusText = record.status === 1 ? '禁用' : '开启';
          return (
            <Fragment>
              <a onClick={() => this.handleModalVisible(true, 'update', record)}>更新</a>
              <Divider type="vertical"/>
              <a className={styles.tableDelete} onClick={() => this.handleStatus(record)}>
                {statusText}
              </a>

              {
                record.status === 2 ? (
                  <span>
                    <Divider type="vertical"/>
                    <a className={styles.tableDelete} onClick={() => this.handleDelete(record)}>
                      删除
                    </a>
                  </span>
                ) : ''
              }
            </Fragment>
          );
        }
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
                新建商品分类
              </Button>
            </div>
          </div>
          <Table defaultExpandAllRows={true} columns={columns} dataSource={list} rowKey="id" />
        </Card>
        <CreateForm {...parentMethods} modalVisible={modalVisible} />
      </PageHeaderWrapper>
    );
  }
}

export default ProductCategoryList;
