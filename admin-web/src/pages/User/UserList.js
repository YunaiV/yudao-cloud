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
  InputNumber, Select
} from 'antd';
const TabPane = Tabs.TabPane;
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './UserList.less';
import PaginationHelper from "../../../helpers/PaginationHelper";

const FormItem = Form.Item;

const statuses = {
  1: '开启',
  2: '关闭',
};

// 列表
function List({ dataSource, loading, pagination, searchParams, dispatch,}) {

  function onPageChange(page) { // 翻页
    dispatch({
      type: 'userList/page',
      payload: {
        pageNo: page.current,
        pageSize: page.pageSize,
        ...searchParams
      }
    })
  }

  const columns = [
    // {
    //   title: 'id',
    //   dataIndex: 'id',
    //   render: text => <strong>{text}</strong>,
    // },
    {
      title: '昵称',
      dataIndex: 'nickname',
    },
    {
      title: '手机号码',
      dataIndex: 'mobile',
    },
    {
      title: '会员卡', // TODO 芋艿，未来增加
    },
    {
      title: '累积交易次数', // TODO 芋艿，未来增加
    },
    {
      title: '累计交易额', // TODO 芋艿，未来增加
    },
    {
      title: '积分', // TODO 芋艿，未来增加
    },
    {
      title: '会员标签', // TODO 芋艿，未来增加
    },
    {
      title: '备注', // TODO 芋艿，未来增加
    },
    {
      title: '状态',
      dataIndex: 'status',
      render: val => statuses[val + ''],
    },
    {
      title: '操作',
      width: 300,
      render: (text, record) => (
        <Fragment>
          {/*<a onClick={() => this.handleModalVisible(true, 'update', record)}>更新</a>*/}
          <a onClick={() => alert('正在开发中')}>编辑</a>
        </Fragment>
      ),
    },
  ];

  // console.log(pagination);

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

// 搜索表单
const SearchForm = Form.create()(props => {
  const {
    form,
    form: { getFieldDecorator },
    dispatch,
    searchParams,
  } = props;

  function search() {
    dispatch({
      type: 'userList/page',
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
          <FormItem label="会员昵称">
            {getFieldDecorator('nickname')(<Input placeholder="请输入" />)}
          </FormItem>
        </Col>
        <Col md={8} sm={24}>
          <FormItem label="会员状态">
            {getFieldDecorator('status')(
              <Select placeholder="请选择" style={{ width: '200px' }}>
                <Option value="1">开启</Option>
                <Option value="2">关闭</Option>
              </Select>
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

// userList
@connect(({ userList }) => ({
  ...userList,
  // list: userList.list.spus,
  // loading: loading.models.userList,
}))

@Form.create()
class UserList extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add update
    initValues: {},
  };

  componentDidMount() {
    const { dispatch, searchParams } = this.props;
    // 查询初始数据
    dispatch({
      type: 'userList/page',
      payload: {
        ...searchParams,
        ...PaginationHelper.defaultPayload,
      },
    });
  }

  handleSortModalVisible = (sortModalVisible, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'userList/setAll',
      payload: {
        sortModalVisible,
        formVals: record || {}
      },
    });
  };

  render() {
    const { dispatch,
      list, listLoading, searchParams, pagination,
      categoryTree, formVals, } = this.props;

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

    return (
      <PageHeaderWrapper title="">
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListOperator}>
              {/*<Button*/}
              {/*  icon="plus"*/}
              {/*  type="primary"*/}
              {/*  onClick={() => alert('正在开发中')}*/}
              {/*>*/}
              {/*  新建会员*/}
              {/*</Button>*/}
            </div>
          </div>
          <SearchForm {...searchFormProps} />
          <List {...listProps} />
        </Card>
      </PageHeaderWrapper>
    );
  }
}

export default UserList;
