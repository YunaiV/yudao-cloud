import React, { PureComponent } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import { Card, Tabs, Table } from 'antd';
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
    this.queryList({ index: 1 });
  }

  queryList = ({ index = 0, pageSize = 10 }, searchParams) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'orderRefunds/list',
      payload: {
        index,
        pageSize,
        ...searchParams,
      },
    });
  };

  handleTabsChange = value => {
    console.log(value);
  };

  handleTableChange = pagination => {
    const { pageSize, current } = pagination;
    this.queryList({ pageSize, index: current });
  };

  render() {
    const { orderRefunds } = this.props;
    const { list, totalCount, index, pageSize } = orderRefunds;

    const columns = [
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
        render() {
          return (
            <div>
              <a>同意</a>
            </div>
          );
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
            <TableSearch />
          </div>

          <Tabs defaultActiveKey={null} onChange={this.handleTabsChange}>
            <Tabs.TabPane tab="全部" key={null} />
            <Tabs.TabPane tab="待处理" key={1} />
            <Tabs.TabPane tab="已处理" key={2} />
            <Tabs.TabPane tab="已完成" key={4} />
          </Tabs>

          <Table
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
