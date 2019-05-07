/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import {
  Card,
  Form,
  Input,
  Row,
  Col,
  Button,
  Modal,
  message,
  Table,
  Divider,
  Tree,
  Tabs,
  TreeSelect,
  Spin,
  InputNumber
} from 'antd';
const TabPane = Tabs.TabPane;
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './ProductSpuList.less';
import PaginationHelper from "../../../helpers/PaginationHelper";
import PicturesWall from "../../components/Image/PicturesWall";

const FormItem = Form.Item;
const { TreeNode } = Tree;

// 列表
function List({ dataSource, loading, pagination, searchParams, dispatch,
                categoryTree, handleSortModalVisible}) {

  const handleTabsChange = (value) => {
    dispatch({
      type: 'productSpuList/page',
      payload: {
        ...searchParams,
        status: value,
        ...PaginationHelper.defaultPayload,
      }
    })
  };

  function onPageChange(page) { // 翻页
    dispatch({
      type: 'productSpuList/page',
      payload: {
        pageNo: page.current,
        pageSize: page.pageSize,
        ...searchParams
      }
    })
  }

  const redirectToUpdate = (spuId) => {
    dispatch({
      type: 'productSpuList/redirectToUpdate',
      payload: spuId,
    });
  };

  const getCategoryName = (cid, array) => {
    // debugger;
    for (let i in array) {
      // debugger;
      const node = array[i];
      if (node.id === cid) {
        return node.name;
      }
      if (!node.children) {
        continue;
      }
      let name = getCategoryName(cid, node.children);
      if (name) {
        return name;
      }
    }
    return undefined;
  };

  const columns = [
    // {
    //   title: 'id',
    //   dataIndex: 'id',
    //   render: text => <strong>{text}</strong>,
    // },
    {
      title: '商品名称',
      dataIndex: 'name',
    },
    {
      title: '商品分类',
      dataIndex: 'cid',
      render: value => getCategoryName(value, categoryTree),
    },
    {
      title: '商品主图',
      dataIndex: 'picUrls',
      render(val) {
        return <img width={120} src={val[0]} />;
      },
    },
    {
      title: '商品库存',
      dataIndex: 'quantity'
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
      title: '操作',
      width: 200,
      render: (text, record) => (
        <Fragment>
          {/*<a onClick={() => this.handleModalVisible(true, 'update', record)}>更新</a>*/}
          <a onClick={() => redirectToUpdate(record.id)}>编辑</a>
          <Divider type="vertical"/>
          <a onClick={() => handleSortModalVisible(true, record)}>
            排序
          </a>
        </Fragment>
      ),
    },
  ];

  // console.log(pagination);

  return (
    <div>
      <Tabs defaultActiveKey={searchParams.status} onChange={handleTabsChange}>
        <TabPane tab="在售中" key={1} />
        <TabPane tab="已售罄" key={2} />
        <TabPane tab="已下架" key={3} />
      </Tabs>
      <Table
        columns={columns}
        dataSource={dataSource}
        rowKey="id"
        pagination={pagination}
        onChange={onPageChange}
        loading={loading} />
    </div>
  )
}

// 搜索表单
const SearchForm = Form.create()(props => {
  const {
    form,
    form: { getFieldDecorator },
    dispatch,
    searchParams,
    categoryTree,
  } = props;

  function search() {
    dispatch({
      type: 'productSpuList/page',
      payload: {
        ...PaginationHelper.defaultPayload,
        ...searchParams,
        ...form.getFieldsValue(),
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

  // 处理分类筛选
  const buildSelectTree = (list) => {
    return list.map(item => {
      let children = [];
      if (item.children) {
        children = buildSelectTree(item.children);
      }
      return {
        title: item.name,
        value: item.id,
        key: item.id,
        children,
        selectable: item.pid > 0
      };
    });
  };
  let categoryTreeSelect = buildSelectTree(categoryTree);

  return (
    <Form onSubmit={handleSubmit} layout="inline">
      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <FormItem label="商品名称">
            {getFieldDecorator('name')(<Input placeholder="请输入" />)}
          </FormItem>
        </Col>
        <Col md={8} sm={24}>
          <FormItem label="商品分类">
            {getFieldDecorator('cid')(
              <TreeSelect
                showSearch
                style={{ width: 200 }}
                dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
                treeData={categoryTreeSelect}
                placeholder="请选择"
              />
            )}
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

//

// 新建 form 表单
const UpdateSortForm = Form.create()(props => {
  const { dispatch, loading, modalVisible, form, handleModalVisible, formVals } = props;

  const okHandle = () => {
    form.validateFields((err, fields) => {
      if (err) return;
      dispatch({
        type: 'productSpuList/updateSort',
        payload: {
          body: {
            id: formVals.id,
            ...fields,
          },
          callback: () => {
            // 清空表单
            form.resetFields();
            // 提示
            message.success('编辑排序成功');
            // 关闭弹窗
            handleModalVisible();
          },
        },
      });
    });
  };

  const title = '编辑排序值';
  const okText = '确定';
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
        <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="排序值">
          {form.getFieldDecorator('sort', {
            rules: [{ required: true, message: '请输入排序值！' }],
            initialValue: formVals.sort,
          })(<InputNumber placeholder="请输入" />)}
        </FormItem>
      </Spin>
    </Modal>
  );
});

// productSpuList
@connect(({ productSpuList, productCategoryList }) => ({
  ...productSpuList,
  // list: productSpuList.list.spus,
  // loading: loading.models.productSpuList,
  categoryTree: productCategoryList.list,
}))

@Form.create()
class ProductSpuList extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add update
    initValues: {},
    roleAssignVisible: false,
    roleAssignRecord: {},
  };

  componentDidMount() {
    const { dispatch } = this.props;
    // 查询初始数据
    dispatch({
      type: 'productSpuList/page',
      payload: {
        status: 1,
        ...PaginationHelper.defaultPayload,
      },
    });
    // 获得商品分类
    dispatch({
      type: 'productCategoryList/tree',
      payload: {},
    });
  }

  redirectToAdd = () => {
    const { dispatch } = this.props;
    dispatch({
      type: 'productSpuList/redirectToAdd',
    });
  };

  handleSortModalVisible = (sortModalVisible, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'productSpuList/setAll',
      payload: {
        sortModalVisible,
        formVals: record || {}
      },
    });
  };

  render() {
    const { dispatch,
      list, listLoading, searchParams, pagination,
      categoryTree, formVals,
      sortModalLoading, sortModalVisible, } = this.props;

    // 列表属性
    const listProps = {
      dataSource: list,
      pagination,
      searchParams,
      dispatch,
      categoryTree,
      loading: listLoading,
      handleSortModalVisible: this.handleSortModalVisible, // Func
    };

    // 搜索表单属性
    const searchFormProps = {
      dispatch,
      categoryTree,
      searchParams,
    };

    // 添加 or 编辑表单属性
    // debugger;
    const updateSortFormProps = {
      modalVisible: sortModalVisible,
      formVals,
      dispatch,
      loading: sortModalLoading,
      handleModalVisible: this.handleSortModalVisible, // Func
    };

    return (
      <PageHeaderWrapper title="">
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListOperator}>
              <Button
                icon="plus"
                type="primary"
                onClick={this.redirectToAdd}
              >
                发布商品
              </Button>
            </div>
          </div>
          <SearchForm {...searchFormProps} />
          <List {...listProps} />
          <UpdateSortForm {...updateSortFormProps} />
        </Card>
      </PageHeaderWrapper>
    );
  }
}

export default ProductSpuList;
