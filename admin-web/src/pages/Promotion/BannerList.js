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
  InputNumber
} from 'antd';
import { checkTypeWithEnglishAndNumbers } from '../../../helpers/validator'
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './BannerList.less';
import moment from "moment";
import PaginationHelper from "../../../helpers/PaginationHelper";

const FormItem = Form.Item;
const { TreeNode } = Tree;
const status = ['未知', '正常', '禁用'];

// 列表
function List ({ dataSource, loading, pagination, searchParams, dispatch,
                 handleModalVisible}) {

  function handleStatus(record) {
    Modal.confirm({
      title: record.status === 1 ? '确认禁用' : '取消禁用',
      content: `${record.username}`,
      onOk() {
        dispatch({
          type: 'bannerList/updateStatus',
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
      content: `${record.title}`,
      onOk() {
        dispatch({
          type: 'bannerList/delete',
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
      title: '标题',
      dataIndex: 'title'
    },
    {
      title: '跳转链接',
      dataIndex: 'url',
    },
    {
      title: '图片',
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
      title: '状态',
      dataIndex: 'status',
      render(val) {
        return <span>{status[val]}</span>; // TODO 芋艿，此处要改
      },
    },
    {
      title: '备注',
      dataIndex: 'memo',
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
      type: 'bannerList/query',
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
      type: 'bannerList/query',
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
          <FormItem label="标题">
            {getFieldDecorator('title')(<Input placeholder="请输入" />)}
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
          type: 'bannerList/add',
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
          type: 'bannerList/update',
          payload: {
            body: {
              id: formVals.id,
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

  const title = modalType === 'add' ? '新建 Banner' : '更新 Banner';
  return (
    <Modal
      destroyOnClose
      title={title}
      visible={modalVisible}
      onOk={okHandle}
      okText='保存'
      onCancel={() => handleModalVisible()}
    >
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="标题">
        {form.getFieldDecorator('title', {
          rules: [{ required: true, message: '请输入标题！'},
            {max: 32, min:2, message: '长度为 2-32 位'},
          ],
          initialValue: formVals.title,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="跳转链接">
        {form.getFieldDecorator('url', {
          rules: [{ required: true, message: '请输入跳转链接！'},
            { type: 'url', message: '必须是 URL！'},
            {max: 255, message: '最大长度为 255 位'},
          ],
          initialValue: formVals.picUrl,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="图片">
        {form.getFieldDecorator('picUrl', {
          rules: [{ required: true, message: '请输入跳转链接！'},],
            initialValue: formVals.picUrl,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="排序值">
        {form.getFieldDecorator('sort', {
          rules: [{ required: true, message: '请输入排序值！' }],
          initialValue: formVals.sort,
        })(<InputNumber placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="备注">
        {form.getFieldDecorator('memo', {
          rules: [{ required: false, message: '请输入备注！' },
            {max: 255, message: '最大长度为 255 位'},
          ],
          initialValue: formVals.memo,
        })(<Input.TextArea placeholder="请输入" />)}
      </FormItem>
    </Modal>
  );
});

@connect(({ bannerList }) => ({
  // list: bannerList.list,
  // pagination: bannerList.pagination,
  ...bannerList,
}))

// 主界面
@Form.create()
class BannerList extends PureComponent {

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'bannerList/query',
      payload: {
        ...PaginationHelper.defaultPayload
      },
    });
  }

  handleModalVisible = (modalVisible, modalType, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'bannerList/setAll',
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
      type: 'bannerList/setAll',
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
                新建 Banner
              </Button>
            </div>
          </div>
          <List {...listProps} />
        </Card>

        <AddOrUpdateForm {...addOrUpdateFormProps} />

      </PageHeaderWrapper>
    );
  }
}

export default BannerList;