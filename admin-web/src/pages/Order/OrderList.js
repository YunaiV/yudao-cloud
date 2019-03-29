import React, { PureComponent } from 'react';
import moment from 'moment';
import { connect } from 'dva';
import { Button, Card, Col, Divider, Form, Input, Row, Tabs, DatePicker, List } from 'antd';

import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import DictionaryText from '@/components/Dictionary/DictionaryText';
import OrderUpdatePayAmount from './OrderUpdatePayAmount';
import dictionary from '@/utils/dictionary';

import styles from './OrderList.less';

const { RangePicker } = DatePicker;
const FormItem = Form.Item;
const { TabPane } = Tabs;

const OrderContent = orderItem => {
  const { dispatch, skuName, skuImage, quantity, price, payAmount, createTime, status } = orderItem;

  const handleUpdatePayAmount = updateOrderItem => {
    dispatch({
      type: 'orderList/changePayAmountVisible',
      payload: {
        payAmountVisible: true,
        payAmount: updateOrderItem.payAmount,
        orderId: updateOrderItem.orderId,
        orderItemId: updateOrderItem.id,
      },
    });
  };

  return (
    <div className={styles.order}>
      <img alt={skuName} className={`${styles.image}`} src={skuImage} />
      <div className={styles.contentItem}>
        <div className={styles.columnName}>(名称)</div>
        <div>
          <a>{skuName}</a>
        </div>
        <div>秋季精选</div>
      </div>
      <div className={styles.contentItem}>
        <div className={styles.columnName}>(金额/物件)</div>
        <div>{quantity}件</div>
        <div>
          {price / 100} 元/{quantity * (price / 100)} 元
        </div>
      </div>
      <div className={styles.contentItem}>
        <div className={styles.columnName}>(购买人)</div>
        <div>范先生</div>
        <div>13302926050</div>
      </div>
      <div className={styles.contentItem}>
        <div className={styles.columnName}>(下单时间)</div>
        <div>{moment(createTime).format('YYYY-MM-DD HH:mm')}</div>
        <div>&nbsp;</div>
      </div>
      <div className={styles.contentItem}>
        <div className={styles.columnName}>(订单状态)</div>
        <div>
          <DictionaryText dicKey={dictionary.ORDER_STATUS} dicValue={status} />
        </div>
        <div>{[0, 1, 2].indexOf(status) ? <Button>取消订单</Button> : ''}</div>
      </div>
      <div className={styles.contentItem}>
        <div className={styles.columnName}>(实付金额)</div>
        <div>{payAmount / 100}元</div>
        <div>
          <a onClick={() => handleUpdatePayAmount(orderItem)}>修改价格</a>
        </div>
      </div>
    </div>
  );
};

const OrderList = props => {
  const { list, dispatch, loading } = props;
  const { pagination, dataSource } = list;

  const paginationProps = {
    ...pagination,
  };

  return (
    <List
      size="large"
      rowKey="id"
      loading={loading}
      pagination={paginationProps}
      dataSource={dataSource}
      renderItem={item => (
        <List.Item>
          <div className={styles.orderGroup}>
            <div className={styles.header}>
              <div>
                <span>订单号: {item.orderNo}</span>
                <Divider type="vertical" />
                <span>支付金额: {item.payAmount / 100} 元</span>
              </div>

              <div>
                <a>查看详情</a>
                <Divider type="vertical" />
                <a>备注</a>
              </div>
            </div>

            {item.orderItems.map(orderItem => {
              return <OrderContent key={orderItem.id} dispatch={dispatch} {...orderItem} />;
            })}
          </div>
        </List.Item>
      )}
    />
  );
};

// SearchForm
const SearchForm = Form.create()(props => {
  const {
    form: { getFieldDecorator },
    form,
    handleSearch,
  } = props;

  const handleFormReset = () => {};

  const onSubmit = e => {
    e.preventDefault();
    form.validateFields((err, fields) => {
      const buildTime = (fieldValue, key) => {
        const res = {};
        if (fieldValue) {
          const keySuffix = key.substring(0, 1).toUpperCase() + key.substring(1);
          // res[`start${keySuffix}`] = fieldValue[0].valueOf();
          res[`start${keySuffix}`] = fieldValue[0].format('YYYY-MM-DD HH:mm:ss');
          // res[`end${keySuffix}`] = fieldValue[1].valueOf();
          res[`end${keySuffix}`] = fieldValue[1].format('YYYY-MM-DD HH:mm:ss');
        }
        return res;
      };

      const timeFields = ['createTime', 'closingTime'];
      const buildSearchParams = fields2 => {
        let res = {};
        Object.keys(fields).map(objectKey => {
          const fieldValue = fields2[objectKey];
          if (timeFields.indexOf(objectKey) !== -1) {
            // 处理时间
            res = {
              ...res,
              ...buildTime(fieldValue, objectKey),
            };
          } else if (fieldValue !== undefined) {
            res[objectKey] = fieldValue;
          }
          return true;
        });
        return res;
      };

      const searchParams = buildSearchParams(fields);
      if (handleSearch) {
        handleSearch(searchParams);
      }
    });
  };

  return (
    <Form onSubmit={onSubmit} layout="inline">
      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <FormItem label="订单id">
            {getFieldDecorator('id')(<Input placeholder="请输入订单id" />)}
          </FormItem>
        </Col>
        <Col md={8} sm={24}>
          <FormItem label="订单号">
            {getFieldDecorator('orderNo')(<Input placeholder="请输入订单号" />)}
          </FormItem>
        </Col>
      </Row>

      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <FormItem label="创建时间">{getFieldDecorator('createTime')(<RangePicker />)}</FormItem>
        </Col>
        <Col md={8} sm={24}>
          <FormItem label="成交时间">{getFieldDecorator('closingTime')(<RangePicker />)}</FormItem>
        </Col>
        <Col md={8} sm={24}>
          <span className={styles.submitButtons}>
            <Button type="primary" htmlType="submit">
              查询
            </Button>
            <Button style={{ marginLeft: 8 }} onClick={handleFormReset}>
              重置
            </Button>
          </span>
        </Col>
      </Row>
    </Form>
  );
});

@connect(({ orderList, loading }) => ({
  orderList,
  list: orderList.list,
  loading: loading.models.orderList,
}))
class BasicList extends PureComponent {
  componentDidMount() {
    const {
      list: { pagination },
    } = this.props;

    this.queryList({
      pageNo: pagination.current,
      pageSize: pagination.pageSize,
    });
  }

  queryList = params => {
    const { dispatch } = this.props;
    // 保存每次操作 searchParams
    this.searchParams = params;
    // dispatch
    dispatch({
      type: 'orderList/queryPage',
      payload: {
        ...params,
      },
    });
  };

  handleEditorClick = () => {
    console.info('edit');
  };

  handleSearch = fields => {
    const {
      list: { pagination },
    } = this.props;

    this.queryList({
      ...fields,
      pageNo: pagination.current,
      pageSize: pagination.pageSize,
    });
  };

  handleTabsChange = key => {
    const params = {
      ...this.searchParams,
      status: key,
    };

    this.queryList(params);
  };

  render() {
    return (
      <PageHeaderWrapper>
        <div className={styles.standardList}>
          <Card
            className={styles.listCard}
            bordered={false}
            title="订单列表"
            style={{ marginTop: 24 }}
            bodyStyle={{ padding: '0 32px 40px 32px' }}
          >
            <div className={styles.tableListForm}>
              <SearchForm {...this.props} handleSearch={this.handleSearch} />
            </div>
            <Tabs defaultActiveKey={null} onChange={this.handleTabsChange}>
              <TabPane tab="全部" key={null} />
              <TabPane tab="待付款" key={0} />
              <TabPane tab="待发货" key={1} />
              <TabPane tab="已发货" key={2} />
              <TabPane tab="已完成" key={3} />
              <TabPane tab="已关闭" key={4} />
            </Tabs>

            <OrderList {...this.props} handleEditorClick={this.handleEditorClick} />
          </Card>
        </div>

        <OrderUpdatePayAmount {...this.props} />
      </PageHeaderWrapper>
    );
  }
}

export default BasicList;
