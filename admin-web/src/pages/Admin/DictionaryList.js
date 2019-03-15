/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import { Card, Form, Input, InputNumber, Select, Button, Modal, message, Table, Divider } from 'antd';
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

  const title = modalType === 'add' ? '新建数据字典' : '编辑数据字典';
  const okText = '保存';
  return (
    <Modal
      destroyOnClose
      title={title}
      visible={modalVisible}
      onOk={okHandle}
      okText={okText}
      onCancel={() => handleModalVisible()}
    >
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="大类枚举值">
        {form.getFieldDecorator('enumValue', {
          rules: [{ required: true, message: '请输入大类枚举值！', min: 2 }],
          initialValue: initValues.enumValue,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="小类数值">
        {form.getFieldDecorator('value', {
          rules: [{ required: true, message: '请输入小类数值！' }],
          initialValue: initValues.value,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="展示名">
        {form.getFieldDecorator('displayName', {
          rules: [{ required: true, message: '请输入展示名！' }],
          initialValue: initValues.displayName,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="排序值">
        {form.getFieldDecorator('sort', {
          rules: [{ required: true, message: '请输入排序值！' }],
          initialValue: initValues.sort,
        })(<InputNumber placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="备注">
        {form.getFieldDecorator('memo', {
          rules: [{ required: true, message: '请输入备注！' }],
          initialValue: initValues.memo,
         })(<Input.TextArea placeholder="请输入" />)}
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
    let that = this;

    const columns = [
      {
        title: '大类枚举值',
        dataIndex: 'enumValue'
      },
      // {
      //   title: '编号',
      //   dataIndex: 'id',
      // },
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
        render: function(text, record) {
          if (!record.id) {
              return '';
          }
          return <Fragment>
                <a onClick={() => that.handleModalVisible(true, 'update', record)}>编辑</a>
                <Divider type="vertical" />
                <a className={styles.tableDelete} onClick={() => that.handleDelete(record)}>
                    删除
                </a>
            </Fragment>
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
                新建数据字典
              </Button>
            </div>
          </div>
          <Table defaultExpandAllRows={true} columns={columns} dataSource={list} rowKey="index" />
        </Card>
        <CreateForm {...parentMethods} modalVisible={modalVisible} />
      </PageHeaderWrapper>
    );
  }
}

export default DictionaryList;
