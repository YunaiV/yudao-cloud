import React, { PureComponent } from 'react';
import { connect } from 'dva';
import { Card, Tabs } from 'antd';
import PageHeaderWrapper from '../../components/PageHeaderWrapper';
import TableSearch from './TableSearch';
import styles from '../List/TableList.less';

/**
 * 订单售后列表
 */
@connect(({ loading }) => ({
  loading: loading.models.orderList,
}))
class OrderRefundsList extends PureComponent {
  handleTabsChange = value => {
    console.log(value);
  };

  render() {
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
        </Card>
      </PageHeaderWrapper>
    );
  }
}

export default OrderRefundsList;
