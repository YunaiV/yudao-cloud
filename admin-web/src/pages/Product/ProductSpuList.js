/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import { Card, Form, Input, Spin, Button, Modal, message, Table, Divider, Tree } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './ProductSpuList.less';

const FormItem = Form.Item;
const { TreeNode } = Tree;

// productSpuList
@connect(({ productSpuList, loading }) => ({
  productSpuList,
  list: productSpuList.list.spus,
  loading: loading.models.productSpuList,
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
    dispatch({
      type: 'productSpuList/page',
      payload: {
        name: '',
        pageNo: 1,
          pageSize: 20,
      },
    });
  }

  redirectToAdd = () => {
    const { dispatch } = this.props;
    dispatch({
      type: 'productSpuList/redirectToAdd',
    });
  };

  redirectToUpdate = (spuId) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'productSpuList/redirectToUpdate',
      payload: spuId,
    });
  };

  render() {
    // debugger;
    const { list, data } = this.props;

    // const { pageNo, pageSize, count, roleTreeData, checkedKeys, assignModalLoading } = data;
    const { modalVisible, modalType, initValues, roleAssignVisible } = this.state;

    const parentMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleModalVisible,
      modalType,
      initValues,
    };

    const columns = [
      {
        title: 'id',
        dataIndex: 'id',
        render: text => <strong>{text}</strong>,
      },
      {
        title: '商品名称',
        dataIndex: 'name',
      },
      {
        title: '商品分类',
        dataIndex: 'cid'
      },
      {
        title: '商品主图',
        dataIndex: 'picUrls',
        render(val) {
          return <img width={120} src={val[0]} />;
          // return 'TODO';
        },
      },
      {
        title: '商品库存',
        dataIndex: 'quantity'
      },
      {
        title: '排序值',
        dataIndex: 'sort',
        render: sort => <span>{sort}</span>,
      },
      {
        title: '创建时间',
        dataIndex: 'createTime',
        sorter: true,
        render: val => <span>{moment(val).format('YYYY-MM-DD')}</span>,
      },
      {
        title: '操作',
        width: 200,
        render: (text, record) => (
          <Fragment>
            {/*<a onClick={() => this.handleModalVisible(true, 'update', record)}>更新</a>*/}
            <a onClick={() => this.redirectToUpdate(record.id)}>更新</a>
          </Fragment>
        ),
      },
    ];

    // const paginationProps = {
    //   current: pageNo,
    //   pageSize: pageSize,
    //   total: count,
    // };

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
          <Table columns={columns} dataSource={list} rowKey="id" />
        </Card>
      </PageHeaderWrapper>
    );
  }
}

export default ProductSpuList;
