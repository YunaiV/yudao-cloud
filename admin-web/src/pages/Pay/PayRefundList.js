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
  InputNumber, DatePicker, Select
} from 'antd';
const TabPane = Tabs.TabPane;
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
const { RangePicker } = DatePicker;

import styles from './PayRefundList.less';
import PaginationHelper from "../../../helpers/PaginationHelper";

const FormItem = Form.Item;

const statuses = {
  1: '处理中',
  2: '成功',
  3: '失败',
};

const payChannels = {
  100: '微信 App 支付',
  101: '微信 JS API 支付',
  200: '支付宝 App 支付',
  9999: 'ping++',
};

// 列表
function List({ dataSource, loading, pagination, searchParams, dispatch,}) {

  function onPageChange(page) { // 翻页
    dispatch({
      type: 'payRefundList/page',
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
      title: '创建时间',
      dataIndex: 'createTime',
      render: val => <span>{moment(val).format('YYYY-MM-DD HH:mm:ss')}</span>,
      width: 120,
    },
    {
      title: '完成时间',
      dataIndex: 'finishTime',
      render: val => val ? <span>{moment(val).format('YYYY-MM-DD HH:mm:ss')}</span> : '',
      width: 120,
    },
    {
      title: '渠道流水号',
      dataIndex: 'tradeNo',
    },
    {
      title: '退款金额',
      dataIndex: 'price',
      render: val => val / 100.0,
    },
    {
      title: '退款状态',
      dataIndex: 'status',
      render: val => statuses[val + ''],
    },
    {
      title: '支付时间',
      dataIndex: 'transaction.finishTime',
      render: val => val ? <span>{moment(val).format('YYYY-MM-DD HH:mm:ss')}</span> : '',
      width: 120,
    },
    {
      title: '商户订单号',
      dataIndex: 'orderId',
    },
    {
      title: '商品名称',
      dataIndex: 'transaction.orderSubject',
    },
    {
      title: '支付金额',
      dataIndex: 'transaction.price',
    },
    {
      title: '支付渠道',
      dataIndex: 'transaction.payChannel',
      render: val => payChannels[val + ''],
    },
    {
      title: '操作',
      width: 120,
      render: (text, record) => (
        <Fragment>
          {/*<a onClick={() => this.handleModalVisible(true, 'update', record)}>更新</a>*/}
          <a onClick={() => alert('正在开发中')}>详情</a>
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
    const getBeginAndEndTime = (key, beginKey, endKey) => {
      let val = form.getFieldsValue()[key];
      if (val && val.length === 2) {
        let res = {};
        res[beginKey] = val[0].format('YYYY-MM-DD HH:mm:ss');
        res[endKey] = val[1].format('YYYY-MM-DD HH:mm:ss');
        return res;
      }
      return {};
    };

    dispatch({
      type: 'payRefundList/page',
      payload: {
        ...PaginationHelper.defaultPayload,
        ...searchParams,
        ...form.getFieldsValue(),
        createTime: undefined,
        finishTime: undefined,
        ...getBeginAndEndTime('createTime', 'createBeginTime', 'createEndTime'),
        ...getBeginAndEndTime('finishTime', 'finishBeginTime', 'finishEndTime'),
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
          <FormItem label="创建时间">{getFieldDecorator('createTime')(<RangePicker style={{ width: '250px' }} />)}</FormItem>
        </Col>
        <Col md={8} sm={24}>
          <FormItem label="完成时间">{getFieldDecorator('finishTime')(<RangePicker style={{ width: '250px' }} />)}</FormItem>
        </Col>
      </Row>
      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <FormItem label="支付渠道">
            {getFieldDecorator('payChannel')(
              <Select placeholder="请选择" style={{ width: '250px' }}>
                {Object.keys(payChannels).map((key, index) => <Option key={key} value={key + ''}>{payChannels[key]}</Option>)}
              </Select>
            )}
          </FormItem>
        </Col>
        <Col md={8} sm={24}>
          <FormItem label="退款状态">
            {getFieldDecorator('status')(
              <Select placeholder="请选择" style={{ width: '250px' }}>
                {Object.keys(statuses).map((key, index) => <Option key={key} value={key + ''}>{statuses[key]}</Option>)}
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

// payRefundList
@connect(({ payRefundList }) => ({
  ...payRefundList,
  // list: payRefundList.list.spus,
  // loading: loading.models.payRefundList,
}))

@Form.create()
class PayTransactionList extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add update
    initValues: {},
  };

  componentDidMount() {
    const { dispatch, searchParams } = this.props;
    // 查询初始数据
    dispatch({
      type: 'payRefundList/page',
      payload: {
        ...searchParams,
        ...PaginationHelper.defaultPayload,
      },
    });
  }

  handleSortModalVisible = (sortModalVisible, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'payRefundList/setAll',
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
              {/*  新建限时折扣*/}
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

export default PayTransactionList;
