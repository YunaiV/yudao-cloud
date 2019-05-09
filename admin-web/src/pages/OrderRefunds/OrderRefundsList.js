import React, { PureComponent } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import { Card, Tabs, Modal, Table, Divider } from 'antd';
import PageHeaderWrapper from '../../components/PageHeaderWrapper';
import DictionaryText from '../../components/Dictionary/DictionaryText';
import TableSearch from './TableSearch';
import styles from '../List/TableList.less';

import dictionary from '../../utils/dictionary';

/**
 * 订单售后列表
 */
@connect(({ orderRefunds, loading }) => ({
  orderRefunds,
  loading: loading.models.orderRefunds,
}))
class OrderRefundsList extends PureComponent {
  componentDidMount() {
    // 查询 list
    this.queryList({ index: 1, pageSize: 10 }, {});
  }

  handleSearch = searchParams => {
    const { orderRefunds } = this.props;
    const { index, pageSize } = orderRefunds;
    this.queryList(
      { index, pageSize },
      {
        ...searchParams,
        ...this.tabsValue,
      }
    );
  };

  queryList = (pageParams, searchParams) => {
    const { dispatch } = this.props;

    this.pageParams = pageParams;
    this.searchParams = searchParams;
    dispatch({
      type: 'orderRefunds/list',
      payload: {
        ...pageParams,
        ...searchParams,
      },
    });
  };

  handleTabsChange = value => {
    this.tabsValue = {
      status: value,
    };
    this.queryList(
      { index: 1, pageSize: 10 },
      {
        ...this.searchParams,
        status: value,
      }
    );
  };

  handleTableChange = pagination => {
    const { pageSize, current } = pagination;
    this.queryList({ pageSize, index: current }, {});
  };

  handleAgreeClick = ({ id }) => {
    const { dispatch } = this.props;
    const self = this;
    Modal.confirm({
      title: '提示消息',
      content: '确认同意!',
      onOk() {
        dispatch({
          type: 'orderRefunds/agree',
          payload: {
            params: {
              id,
            },
            callback: () => {
              self.queryList(self.pageParams, self.searchParams);
            },
          },
        });
      },
      onCancel() {
        console.log('Cancel');
      },
    });
  };

  handleRefuse = ({ id }) => {
    const { dispatch } = this.props;
    const self = this;
    Modal.confirm({
      title: '提示消息',
      content: '确认拒绝!',
      onOk() {
        dispatch({
          type: 'orderRefunds/refuse',
          payload: {
            params: {
              id,
            },
            callback: () => {
              self.queryList(self.pageParams, self.searchParams);
            },
          },
        });
      },
      onCancel() {
        console.log('Cancel');
      },
    });
  };

  handleConfirmReceipt = ({ id }) => {
    const { dispatch } = this.props;
    const self = this;
    Modal.confirm({
      title: '提示消息',
      content: '确认收货!',
      onOk() {
        dispatch({
          type: 'orderRefunds/confirmReceipt',
          payload: {
            params: {
              id,
            },
            callback: () => {
              self.queryList(self.pageParams, self.searchParams);
            },
          },
        });
      },
      onCancel() {
        console.log('Cancel');
      },
    });
  };

  handleConfirmRefund = ({ id }) => {
    const { dispatch } = this.props;
    const self = this;
    Modal.confirm({
      title: '提示消息',
      content: '确认退款!',
      onOk() {
        dispatch({
          type: 'orderRefunds/confirmRefund',
          payload: {
            params: {
              id,
            },
            callback: () => {
              self.queryList(self.pageParams, self.searchParams);
            },
          },
        });
      },
      onCancel() {
        console.log('Cancel');
      },
    });
  };

  render() {
    const { orderRefunds, loading } = this.props;
    const { list, totalCount, index, pageSize } = orderRefunds;

    const columns = [
      {
        title: '订单号',
        dataIndex: 'orderId',
        key: 'orderId',
      },
      {
        title: '服务编号',
        dataIndex: 'serviceNumber',
        key: 'serviceNumber',
      },
      {
        title: '服务类型',
        dataIndex: 'serviceType',
        key: 'serviceType',
        render(serviceType) {
          return (
            <DictionaryText dicKey={dictionary.ORDER_RETURN_SERVICE_TYPE} dicValue={serviceType} />
          );
        },
      },
      {
        title: '退货原因',
        dataIndex: 'reason',
        key: 'reason',
        render(reason) {
          return <DictionaryText dicKey={dictionary.ORDER_RETURN_REASON} dicValue={reason} />;
        },
      },
      {
        title: '备注',
        dataIndex: 'describe',
        key: 'describe',
      },
      {
        title: '状态',
        dataIndex: 'status',
        key: 'status',
        render(status) {
          return <DictionaryText dicKey={dictionary.ORDER_RETURN_STATUS} dicValue={status} />;
        },
      },
      {
        title: '同意时间',
        dataIndex: 'approvalTime',
        key: 'approvalTime',
        render(approvalTime) {
          if (approvalTime) {
            return <div>{moment(approvalTime).format('YYYY-MM-DD HH:mm')}</div>;
          }
          return <div>无</div>;
        },
      },
      {
        title: '申请时间',
        dataIndex: 'createTime',
        key: 'createTime',
        render(createTime) {
          return <div>{moment(createTime).format('YYYY-MM-DD HH:mm')}</div>;
        },
      },
      {
        title: '操作',
        render: row => {
          const { status } = row;
          let buttons;
          if (status === 1) {
            buttons = (
              <div>
                <a onClick={this.handleAgreeClick.bind(this, row)}>同意</a>
                <Divider type="vertical" />
                <a onClick={this.handleRefuse.bind(this, row)}>拒绝</a>
              </div>
            );
          } else if (status === 2) {
            buttons = (
              <div>
                <a onClick={this.handleConfirmReceipt.bind(this, row)}>确认收货</a>
              </div>
            );
          } else if (status === 5) {
            buttons = (
              <div>
                <a onClick={this.handleConfirmRefund.bind(this, row)}>退款</a>
              </div>
            );
          }
          return buttons;
        },
      },
    ];

    const pagination = {
      total: totalCount,
      index,
      pageSize,
    };

    return (
      <PageHeaderWrapper>
        <Card>
          <div className={styles.tableListForm}>
            <TableSearch handleSearch={this.handleSearch} />
          </div>

          <Tabs defaultActiveKey={null} onChange={this.handleTabsChange}>
            <Tabs.TabPane tab="全部" key={null} />
            <Tabs.TabPane tab="待处理" key={1} />
            <Tabs.TabPane tab="待收货" key={2} />
            <Tabs.TabPane tab="已收货" key={5} />
            <Tabs.TabPane tab="已完成" key={6} />
            <Tabs.TabPane tab="已拒绝" key={3} />
          </Tabs>

          <Table
            loading={loading}
            rowKey="id"
            dataSource={list}
            columns={columns}
            pagination={pagination}
            onChange={this.handleTableChange}
          />
        </Card>
      </PageHeaderWrapper>
    );
  }
}

export default OrderRefundsList;
