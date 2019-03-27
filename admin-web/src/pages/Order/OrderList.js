import React, { Fragment, PureComponent } from 'react';
import moment from 'moment';
import { connect } from 'dva';
import { Button, Card, Col, Divider, Form, Input, Row, Table, DatePicker } from 'antd';

import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import DictionaryText from '@/components/Dictionary/DictionaryText';
import DictionarySelect from '@/components/Dictionary/DictionarySelect';
import dictionary from '@/utils/dictionary';

import styles from './OrderList.less';

const { RangePicker } = DatePicker;
const FormItem = Form.Item;

const OrderList = props => {
  const { list, dispatch, loading, handleModalVisible } = props;

  // 翻页
  const onPageChange = page => {
    const { searchParams } = props;
    dispatch({
      type: 'adminList/query',
      payload: {
        pageNo: page.current,
        pageSize: page.pageSize,
        ...searchParams,
      },
    });
  };

  const columns = [
    {
      title: '订单id',
      dataIndex: 'id',
    },
    {
      title: '用户',
      dataIndex: 'userId',
    },
    {
      title: '订单号',
      dataIndex: 'orderNo',
    },
    {
      title: '金额',
      dataIndex: 'price',
      render(val) {
        return <span>{val} 元</span>;
      },
    },
    {
      title: '状态',
      dataIndex: 'status',
      render(val) {
        return <DictionaryText dicKey={dictionary.ORDER_STATUS} dicValue={val} />;
      },
    },
    {
      title: '付款时间',
      dataIndex: 'paymentTime',
      render: val => (!val ? '' : <span>{moment(val).format('YYYY-MM-DD HH:mm')}</span>),
    },
    {
      title: '发货时间',
      dataIndex: 'deliveryTime',
      render: val => (!val ? '' : <span>{moment(val).format('YYYY-MM-DD HH:mm')}</span>),
    },
    {
      title: '收货时间',
      dataIndex: 'receiverTime',
      render: val => (!val ? '' : <span>{moment(val).format('YYYY-MM-DD HH:mm')}</span>),
    },
    {
      title: '成交时间',
      dataIndex: 'closingTime',
      render: val => (!val ? '' : <span>{moment(val).format('YYYY-MM-DD HH:mm')}</span>),
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      render: val => (!val ? '' : <span>{moment(val).format('YYYY-MM-DD HH:mm')}</span>),
    },
    {
      title: '操作',
      width: 100,
      render: (text, record) => {
        return (
          <Fragment>
            <a onClick={() => handleModalVisible(true, 'update', record)}>编辑</a>
            <Divider type="vertical" />
          </Fragment>
        );
      },
    },
  ];

  const tableScroll = {
    x: 1400,
  };

  return (
    <Table
      rowKey="id"
      columns={columns}
      dataSource={list.dataSource}
      loading={loading}
      pagination={list.pagination}
      onChange={onPageChange}
      scroll={tableScroll}
    />
  );
};

// SearchForm
const SearchForm = props => {
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
        <Col md={8} sm={24}>
          <FormItem label="状态">
            {getFieldDecorator('status', {})(<DictionarySelect dicKey={dictionary.ORDER_STATUS} />)}
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
};

@connect(({ orderList, loading }) => ({
  orderList,
  list: orderList.list,
  loading: loading.models.orderList,
}))
@Form.create()
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
            <OrderList {...this.props} handleEditorClick={this.handleEditorClick} />
          </Card>
        </div>
      </PageHeaderWrapper>
    );
  }
}

export default BasicList;
