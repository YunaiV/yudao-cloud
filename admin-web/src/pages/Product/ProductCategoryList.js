/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import {Card, Form, Input, Select, Button, Modal, message, Table, Divider, InputNumber, TreeSelect, Spin} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './ProductCategoryList.less';
import PicturesWall from "../../components/Image/PicturesWall";

const FormItem = Form.Item;
const { Option } = Select;
const status = ['未知', '开启', '禁用'];

// 列表
function List({ dataSource, loading, dispatch,
                handleModalVisible}) {

  function handleStatus(record) {
    Modal.confirm({
      title: record.status === 1 ? '确认禁用？' : '确认开启？',
      content: `${record.name}`,
      onOk() {
        dispatch({
          type: 'productCategoryList/updateStatus',
          payload: {
            body: {
              id: record.id,
              status: record.status === 1 ? 2 : 1,
            }
          },
        });
      },
      onCancel() {},
    });
  }

  function handleDelete(record) {
    Modal.confirm({
      title: `确认删除?`,
      content: `${record.name}`,
      onOk() {
        dispatch({
          type: 'productCategoryList/delete',
          payload: {
            id: record.id,
          },
        });
      },
      onCancel() {},
    });
  }

  const columns = [
    // {
    //   title: 'id',
    //   dataIndex: 'id',
    //   render: text => <strong>{text}</strong>,
    // },
    {
      title: '分类名称',
      dataIndex: 'name',
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
        return <span>{status[val]}</span>;
      },
    },
    {
      title: '描述',
      dataIndex: 'description',
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      render: val => <span>{moment(val).format('YYYY-MM-DD')}</span>,
    },
    {
      title: '操作',
      render: (text, record) => {
        const statusText = record.status === 1 ? '禁用' : '开启';
        return (
          <Fragment>
            <a onClick={() => handleModalVisible(true, 'update', record)}>编辑</a>
            <Divider type="vertical"/>
            <a className={styles.tableDelete} onClick={() => handleStatus(record)}>
              {statusText}
            </a>

            {
              record.status === 2 ? (
                <span>
                    <Divider type="vertical"/>
                    <a className={styles.tableDelete} onClick={() => handleDelete(record)}>
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
    <Table
      defaultExpandAllRows={true}
      columns={columns}
      loading={loading}
      rowKey="id"
      dataSource={dataSource}  />
  )
}

// 新建 form 表单
const AddOrUpdateForm = Form.create()(props => {
  const { dispatch, loading, modalVisible, form, handleModalVisible, modalType, categoryTree, formVals } = props;
  let picturesWall = null;

  const okHandle = () => {
    form.validateFields((err, fields) => {
      if (err) return;
      if (modalType === 'add') {
        dispatch({
          type: 'productCategoryList/add',
          payload: {
            body: {
              ...fields,
              picUrl: picturesWall ? picturesWall.getUrl() : undefined,
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
      } else {
        dispatch({
          type: 'productCategoryList/update',
          payload: {
            body: {
              ...formVals,
              ...fields,
              picUrl: picturesWall ? picturesWall.getUrl() : undefined,
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

  function onPidChange(value) {
    formVals.pid = parseInt(value);
  }

  // 处理分类筛选
  const buildSelectTree = (list) => {
    return list.map(item => {
      let children = [];
      // if (item.children) { // 暂时不允许添加二级分类。
      //   children = buildSelectTree(item.children);
      // }
      return {
        title: item.name,
        value: item.id,
        key: item.id,
        children,
      };
    });
  };
  let categoryTreeSelect = buildSelectTree(categoryTree);
  categoryTreeSelect.unshift({
    title: '无父分类',
    value: 0,
    key: 0,
    children: [],
  });

  const title = modalType === 'add' ? '新建分类' : '编辑分类';
  const okText = modalType === 'add' ? '新建' : '编辑';
  return (
    <Modal
      destroyOnClose
      title={title}
      visible={modalVisible}
      onOk={okHandle}
      okText={okText}
      onCancel={() => handleModalVisible()}
    >
      <Spin spinning={loading}>
        <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="分类名称">
          {form.getFieldDecorator('name', {
            rules: [{ required: true, message: '请输入分类名称！', min: 2 }],
            initialValue: formVals.name,
          })(<Input placeholder="请输入" />)}
        </FormItem>
        <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="父分类">
          {form.getFieldDecorator('pid', {
            rules: [{ required: true, message: '请选择父分类！' }],
            initialValue: formVals.pid,
          })(
            <TreeSelect
              showSearch
              style={{ width: 300 }}
              dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
              treeData={categoryTreeSelect}
              placeholder="选择父分类"
              onChange={onPidChange}
            />
          )}
        </FormItem>
        {
          formVals.pid > 0 ? (
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="分类图片"
                      extra="建议尺寸：480*480PX，大小不超过 2M" required={true}>
              <PicturesWall urls={formVals.picUrl ? [formVals.picUrl] : undefined} ref={(node) => picturesWall = node} maxLength={1} />
            </FormItem>
          ) : ''
        }
        <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="排序值">
          {form.getFieldDecorator('sort', {
            rules: [{ required: true, message: '请输入排序值！' }],
            initialValue: formVals.sort,
          })(<InputNumber placeholder="请输入" />)}
        </FormItem>
        <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="描述">
          {form.getFieldDecorator('description', {
            rules: [{ required: true, message: '请输入描述！' }],
            initialValue: formVals.description,
          })(<Input.TextArea placeholder="请输入" />)}
        </FormItem>
      </Spin>
    </Modal>
  );
});

@connect(({ productCategoryList, }) => ({
  ...productCategoryList,
  // list: productCategoryList.list,
  // loading: loading.models.productCategoryList,
}))

@Form.create()
class ProductCategoryList extends PureComponent {

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'productCategoryList/tree',
      payload: {},
    });
  }

  handleModalVisible = (modalVisible, modalType, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'productCategoryList/setAll',
      payload: {
        modalVisible,
        modalType,
        formVals: record || {}
      },
    });
  };

  render() {
    const { dispatch,
      list,listLoading,
      modalVisible, modalType, formVals, modalLoading,} = this.props;

    // 列表属性
    const listProps = {
      dataSource: list,
      dispatch,
      loading: listLoading,
      handleModalVisible: this.handleModalVisible, // Function
    };

    // 添加 or 编辑表单属性
    const addOrUpdateFormProps = {
      modalVisible,
      modalType,
      formVals,
      dispatch,
      loading: modalLoading,
      categoryTree: list,
      handleModalVisible: this.handleModalVisible, // Function
    };

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
                新建分类
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

export default ProductCategoryList;
