import React, { PureComponent, Fragment } from 'react';
import { Button, Card, Table, Form, Divider } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import { connect } from 'dva';
import styles from './DeptmentList.less';
import PaginationHelper from '../../../helpers/PaginationHelper';
import moment from 'moment';

@connect(({ deptmentList, loading }) => ({
  deptmentList,
  deptmentData: deptmentList.deptmentData,
  loading: loading.models.deptmentList,
}))
@Form.create()
export default class DepetmentList extends PureComponent {
  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'deptmentList/getDeptmentList',
      payload: {
        ...PaginationHelper.defaultPayload,
      },
    });
  }

  render() {
    const { deptmentData, deptmentList } = this.props;
    const columns = [
      {
        title: '部门名称',
        dataIndex: 'name',
      },
      {
        title: '排序',
        dataIndex: 'sort',
      },
      {
        title: '创建时间',
        dataIndex: 'createTime',
        sorter: true,
        render: val => <span>{moment(val).format('YYYY-MM-DD')}</span>,
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            <a onClick={() => this.handleModalVisible(true, 'update', record)}>编辑</a>
            <Divider type="vertical" />
            <a className={styles.tableDelete} onClick={() => this.handleDelete(record)}>
              删除
            </a>
          </Fragment>
        ),
      },
    ];

    // const {
    // 	deptmentList: {deptmentData},
    // 	loading,
    // } = this.props;

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
                新建部门
              </Button>
            </div>
          </div>
          <Table
            defaultExpandAllRows={true}
            columns={columns}
            dataSource={deptmentData.list ? deptmentData.list : []}
            rowKey="id"
          />
        </Card>
      </PageHeaderWrapper>
    );
  }
}
