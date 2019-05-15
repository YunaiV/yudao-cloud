/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import {
  Card,
  Form,
  Input,
  Select,
  Button,
  Modal,
  message,
  Table,
  TreeSelect,
  Radio,
  Divider,
  Icon, InputNumber,
} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './ResourceList.less';

const RadioGroup = Radio.Group;
const FormItem = Form.Item;
const { Option } = Select;
const TextArea = Input.TextArea;
const types = ['未知', '菜单', '按钮'];

// 添加 form 表单
const CreateForm = Form.create()(props => {
  const {
    modalVisible,
    form,
    handleAdd,
    handleModalVisible,
    modalType,
    initValues,
    selectTree,
  } = props;

  const okHandle = () => {
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      let pid = fieldsValue.pid;
      if (fieldsValue.pid) {
        pid = pid.split('-')[1];
        fieldsValue.pid = pid;
      }
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

  function onTypeChange(event) {
    initValues.type = parseInt(event.target.value);
  }

  // 给 type 赋予初始值
  initValues.type = initValues.type || 1;

  const title = modalType === 'add' ? '添加一个权限' : '编辑一个权限';
  const okText = modalType === 'add' ? '添加' : '编辑';
  return (
    <Modal
      destroyOnClose
      title={title}
      visible={modalVisible}
      onOk={okHandle}
      okText={okText}
      onCancel={() => handleModalVisible()}
    >
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="类型">
        {form.getFieldDecorator('type', {
          initialValue: initValues.type,
        })(
          <RadioGroup onChange={onTypeChange}>
            <Radio value={1}>菜单</Radio>
            <Radio value={2}>按钮</Radio>
          </RadioGroup>
        )}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="名称">
        {form.getFieldDecorator('displayName', {
          rules: [{ required: true, message: '请输入名称！', min: 2 }],
          initialValue: initValues.displayName,
        })(<Input placeholder="名称" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="父级菜单">
        {form.getFieldDecorator('pid', {
          rules: [{ required: true, message: '请选择父级编号！' }],
          initialValue:
            initValues.pid === 0
              ? `根节点-${initValues.pid}`
              : initValues.pid ? `${initValues.displayName}-${initValues.pid}` : undefined,
        })(
          <TreeSelect
            showSearch
            style={{ width: 300 }}
            dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
            treeData={selectTree}
            placeholder="选择父级菜单"
          />
        )}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="排序">
        {form.getFieldDecorator('sort', {
          rules: [{ required: true, message: '请输入排序！' }],
          initialValue: initValues.sort,
        })(<InputNumber placeholder="请输入" />)}
      </FormItem>
      {
        initValues.type === 1 ? (
          <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="图标">
            {form.getFieldDecorator('icon', {
              rules: [{ required: true, message: '请输入图标！' }],
              initialValue: initValues.icon,
            })(<Input placeholder="图标" />)}
          </FormItem>
        ) : ''
      }
      {
        initValues.type === 1 ? (
          <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="操作">
            {form.getFieldDecorator('handler', {
              initialValue: initValues.handler,
            })(<Input placeholder="操作" />)}
          </FormItem>
        ) : ''
      }
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="权限标识">
        {form.getFieldDecorator('permissions', {
          initialValue: initValues.permissions,
        })(<TextArea placeholder="多个用逗号进行分割，例如：system.admin.add,system.admin.update" />)}
      </FormItem>
    </Modal>
  );
});

@connect(({ resourceList, loading }) => ({
  resourceList,
  list: resourceList.list,
  loading: loading.models.resourceList,
}))
@Form.create()
class ResourceList extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add update
    initValues: {},
  };

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'resourceList/tree',
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
        type: 'resourceList/add',
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
        type: 'resourceList/update',
        payload: {
          body: {
            ...initValues,
            ...fields,
          },
          callback: () => {
            message.success('编辑成功');
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
          type: 'resourceList/delete',
          payload: {
            id: row.id,
          },
        });
      },
      onCancel() {},
    });
  }

  render() {
    const { list, resourceList } = this.props;
    const { selectTree } = resourceList;
    const { modalVisible, modalType, initValues } = this.state;
    const parentMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleModalVisible,
      modalType,
      initValues,
    };

    const columns = [
      {
        title: '名称',
        dataIndex: 'displayName',
      },
      {
        title: '图标',
        dataIndex: 'icon',
        render: text => text ? <Icon type={text} /> : '',
      },
      {
        title: '类型',
        dataIndex: 'type',
        render(val) {
          return <span>{types[val]}</span>;
        },
      },
      {
        title: '排序',
        dataIndex: 'sort',
      },
      {
        title: '操作',
        dataIndex: 'handler',
        width: 200,
        render: val => <span>{val}</span>,
      },
      {
        title: '权限标识',
        dataIndex: 'permissions',
        width: 300,
        render(permissions) {
          let text = '';
          if (permissions) {
            for (let i in permissions) {
              if (i > 0) {
                text += '  ';
              }
              text += permissions[i];
            }
          }
          return (<span>{text}</span>);
        }
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
            <a onClick={() => this.handleModalVisible(true, 'update', record)}>编辑</a>
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
                新建权限
              </Button>
            </div>
          </div>
          <Table defaultExpandAllRows={true} columns={columns} dataSource={list} rowKey="id" />
        </Card>
        <CreateForm {...parentMethods} selectTree={selectTree} modalVisible={modalVisible} />
      </PageHeaderWrapper>
    );
  }
}

export default ResourceList;
