import React, { PureComponent, Fragment } from 'react';
import { Button, Card, Table, Form, Divider, Modal, Input, TreeSelect, message } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import { connect } from 'dva';
import styles from './DeptmentList.less';
import PaginationHelper from '../../../helpers/PaginationHelper';
import moment from 'moment';
const FormItem = Form.Item;
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

  const title = modalType === 'add' ? '添加部门' : '编辑部门';
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
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="部门名称">
        {form.getFieldDecorator('name', {
          initialValue: initValues.name,
          rules: [{ required: true, message: '请输入部门名称！', min: 2 }],
        })(<Input placeholder="部门名称" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="部门排序">
        {form.getFieldDecorator('sort', {
          initialValue: initValues.sort,
        })(<Input placeholder="部门排序" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="父级部门">
        {form.getFieldDecorator('pid', {
          rules: [{ required: true, message: '请选择父级编号！' }],
          initialValue:
            initValues.pid === 0
              ? `根节点-${initValues.pid}`
              : initValues.pid
              ? `${initValues.name}-${initValues.pid}`
              : undefined,
        })(
          <TreeSelect
            showSearch
            style={{ width: 300 }}
            dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
            treeData={selectTree}
            placeholder="选择父级部门"
          />
        )}
      </FormItem>
    </Modal>
  );
});

@connect(({ deptmentList, loading }) => ({
  deptmentList,
  deptmentData: deptmentList.deptmentData,
  loading: loading.models.deptmentList,
}))
@Form.create()
export default class DepetmentList extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add or update
    initValues: {},
  };

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'deptmentList/getDeptmentList',
      payload: {
        ...PaginationHelper.defaultPayload,
      },
    });
  }

  handleModalVisible = (flag, modalType, initValues) => {
    this.setState({
      modalVisible: !!flag,
      initValues: initValues || {},
      modalType: modalType || 'add',
    });
    if (flag) {
      //query treeSelect
      const { dispatch } = this.props;
      dispatch({
        type: 'deptmentList/getDeptmentAll',
        payload: {},
      });
    }
  };

  handleAdd = ({ fields, modalType, initValues }) => {
    const { dispatch } = this.props;
    if (modalType === 'add') {
      dispatch({
        type: 'deptmentList/add',
        payload: {
          body: {
            ...fields,
          },
          onSuccess: () => {
            message.success('添加成功');
            this.handleModalVisible();
          },
          onFail: response => {
            message.warn('添加失败' + response.message);
          },
        },
      });
    } else {
      dispatch({
        type: 'deptmentList/update',
        payload: {
          body: {
            ...initValues,
            ...fields,
          },
          onSuccess: () => {
            message.success('更新成功成功');
            this.handleModalVisible();
          },
          onFail: response => {
            message.warn('更新失败' + response.message);
          },
        },
      });
    }
  };

  render() {
    const { deptmentData, deptmentList } = this.props;
    const { selectTree } = deptmentList;
    const { modalVisible, modalType, initValues } = this.state;
    const parentMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleModalVisible,
      modalType,
      initValues,
    };
    const columns = [
      {
        title: '部门名称',
        dataIndex: 'name',
      },
      {
        title: '排序',
        dataIndex: 'sort',
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

    // const {
    // 	deptmentList: {deptmentData},
    // 	loading,
    // } = this.props;

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
                新建部门
              </Button>
            </div>
          </div>
          <Table
            defaultExpandAllRows={true}
            columns={columns}
            dataSource={deptmentData.list ? deptmentData.list : []}
            rowKey="id"
          />
        </Card>
        <CreateForm {...parentMethods} selectTree={selectTree} modalVisible={modalVisible} />
      </PageHeaderWrapper>
    );
  }
}
