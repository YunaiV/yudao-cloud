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
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './ProductSpuList.less';
import PaginationHelper from "../../../helpers/PaginationHelper";
import PicturesWall from "../../components/Image/PicturesWall";

const FormItem = Form.Item;

const status = ['未知', '开启', '禁用'];
// 列表
function List({ dataSource, loading, pagination, searchParams, dispatch,
                categoryTree,handleModalVisible}) {


  function onPageChange(page) { // 翻页
    dispatch({
      type: 'productBrandList/page',
      payload: {
        pageNo: page.current,
        pageSize: page.pageSize,
        ...searchParams
    }
  })
  }


  const columns = [
    {
      title: '品牌名称',
      dataIndex: 'name',
    },
    {
      title: '品牌描述',
      dataIndex: 'description'
    },
    {
      title: '品牌图片',
      dataIndex: 'picUrl',
      render(val) {return <img width={120} src={val} />;},
    },
    {
      title: '状态',
      dataIndex: 'status',
      render(val) {return <span>{status[val]}</span>;},
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      render: val => <span>{moment(val).format('YYYY-MM-DD')}</span>,
    },
  {
    title: '操作',
    width: 200,
    render: (text, record) => {
    const statusText = record.status === 1 ? '禁用' : '开启';
    return (
      <Fragment>
      <a onClick={() => handleModalVisible(true, 'update', record)}>编辑</a>
       <Divider type="vertical"/>
      <a className={styles.tableDelete} onClick={() => handleStatus(record)}>{statusText}</a>
      <Divider type="vertical"/>
      <a className={styles.tableDelete} onClick={() => handleDelete(record)}>删除</a>
      </Fragment>
  );
  },
  },
];


  return (
    <div>

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

// 新建 form 表单
  const AddOrUpdateForm = Form.create()(props => {
    const { dispatch, loading, modalVisible, form, handleModalVisible, modalType, categoryTree, formVals } = props;
  let picturesWall = null;

  const okHandle = () => {
    form.validateFields((err, fields) => {
      if (err) return;
    if (modalType === 'add') {
      dispatch({
        type: 'productBrandList/add',
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


  const title = modalType === 'add' ? '新建品牌' : '编辑品牌';
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
    <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="品牌名称">
    {form.getFieldDecorator('name', {
      rules: [{ required: true, message: '请输入品牌名称！', min: 2 }],
      initialValue: formVals.name,
    })(<Input placeholder="请输入" />)}
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
    type: 'productBrandList/page',
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




return (
  <Form onSubmit={handleSubmit} layout="inline">
  <Row gutter={{ md: 8, lg: 24, xl: 48 }}>

<Col md={8} sm={24}>
  <FormItem label="品牌名称">
  {getFieldDecorator('name')(<Input placeholder="请输入" />)}
</FormItem>
</Col>

  <Col md={8} sm={24}>
    <FormItem label="品牌描述">
    {getFieldDecorator('description')(<Input placeholder="请输入" />)}
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




// productSpuList
@connect(({ productBrandList}) => ({
  ...productBrandList
}))

@Form.create()
class ProductBrandList extends PureComponent {
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
      type: 'productBrandList/page',
      payload: {
        ...PaginationHelper.defaultPayload,
    },
  });
  }



  handleModalVisible = (modalVisible, modalType, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'productBrandList/add',
      payload: {
        modalVisible,
        modalType,
        formVals: record || {}
      },
    });
  };


render() {
  const { dispatch,
    list, listLoading, searchParams, pagination,
    categoryTree, modalType,formVals,
    modalVisible,modalLoading} = this.props;

  // 列表属性
  const listProps = {
    dataSource: list,
    pagination,
    searchParams,
    dispatch,
    categoryTree,
    loading: listLoading,
    handleModalVisible: this.handleModalVisible, // Function
  };

  // 搜索表单属性
  const searchFormProps = {
    dispatch,
    categoryTree,
    searchParams,
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
          <Button icon="plus" type="primary" onClick={() => this.handleModalVisible(true, 'add', {})} >
          新建品牌
          </Button>
        </div>
      </div>
    <SearchForm {...searchFormProps} />
    <List {...listProps} />
    </Card>

    <AddOrUpdateForm {...addOrUpdateFormProps} />
    </PageHeaderWrapper>
);
}
}

export default ProductBrandList;
