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
const TabPane = Tabs.TabPane;
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './FullPrivilegeList.less';
import PaginationHelper from "../../../helpers/PaginationHelper";

const FormItem = Form.Item;

const statuses = {
  10: '未开始',
  20: '进行中',
  30: '已结束',
  40: '已撤销',
  50: '已删除',
};

const meetTypes = {
  1: '满 N 元减/送',
  2: '满 N 件减/送',
};

// 列表
function List({ dataSource, loading, pagination, searchParams, dispatch,}) {

  const handleTabsChange = (value) => {
    dispatch({
      type: 'fullPrivilegeList/page',
      payload: {
        ...searchParams,
        status: value,
        ...PaginationHelper.defaultPayload,
      }
    })
  };

  function onPageChange(page) { // 翻页
    dispatch({
      type: 'fullPrivilegeList/page',
      payload: {
        pageNo: page.current,
        pageSize: page.pageSize,
        ...searchParams
      }
    })
  }

  function formatFullPrivilegeText(activity) {
    let text = '';
    let fullPrivilege = activity.fullPrivilege;
    for (let i in fullPrivilege.privileges) {
      let privilege = fullPrivilege.privileges[i];
      if (i > 0) {
        text += ';';
      }
      if (fullPrivilege.cycled) {
        text += '每';
      }
      if (privilege.meetType === 1) {
        text += '满 ' + privilege.meetValue / 100.0 + ' 元,';
      } else if (privilege.meetType === 2) {
        text += '满 ' + privilege.meetValue + ' 件,';
      }
      if (privilege.preferentialType === 1) {
        text += '减 ' + privilege.preferentialValue / 100.0 + ' 元';
      } else if (privilege.preferentialType === 2) {
        text += '打 ' + privilege.preferentialValue / 10.0 + ' 折';
      }
    }
    return text;
  };

  const columns = [
    // {
    //   title: 'id',
    //   dataIndex: 'id',
    //   render: text => <strong>{text}</strong>,
    // },
    {
      title: '活动名称',
      dataIndex: 'title',
    },
    {
      title: '类型',
      dataIndex: 'fullPrivilege',
      render: val => meetTypes[val.privileges[0].meetType + ''],
    },
    {
      title: '活动详情',
      render: (text, record) => formatFullPrivilegeText(record),
    },
    {
      title: '活动时间',
      render: (text, record) => (
        <span>
          {moment(record.startTime).format('YYYY-MM-DD HH:mm:ss')}
          &nbsp;至&nbsp;
          {moment(record.endTime).format('YYYY-MM-DD HH:mm:ss')}
        </span>
      ),
    },
    {
      title: '状态',
      dataIndex: 'status',
      render: val => statuses[val + ''],
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      render: val => <span>{moment(val).format('YYYY-MM-DD')}</span>,
    },
    {
      title: '操作',
      width: 300,
      render: (text, record) => (
        <Fragment>
          {/*<a onClick={() => this.handleModalVisible(true, 'update', record)}>更新</a>*/}
          <a onClick={() => alert('正在开发中')}>编辑</a>
          {
            record.status === 10 || record.status === 20 ? <span>
              <Divider type="vertical" />
              <a onClick={() => alert('正在开发中')}>
                撤销
              </a>
            </span> : undefined
          }
          {
            record.status !== 50 ? <span>
              <Divider type="vertical" />
              <a className={styles.tableDelete} onClick={() => alert('正在开发中')}>
                删除
              </a>
            </span> : undefined
          }
        </Fragment>
      ),
    },
  ];

  // console.log(pagination);

  return (
    <div>
      <Tabs defaultActiveKey={searchParams.status} onChange={handleTabsChange}>
        <TabPane tab="所有活动" key="ALL" />
        <TabPane tab="未开始" key="WAIT" />
        <TabPane tab="进行中" key="RUN" />
        <TabPane tab="已结束" key="END" />
        <TabPane tab="已撤销" key="INVALID" />
      </Tabs>
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
      type: 'fullPrivilegeList/page',
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
          <FormItem label="活动名称">
            {getFieldDecorator('title')(<Input placeholder="请输入" />)}
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

// fullPrivilegeList
@connect(({ fullPrivilegeList }) => ({
  ...fullPrivilegeList,
  // list: fullPrivilegeList.list.spus,
  // loading: loading.models.fullPrivilegeList,
}))

@Form.create()
class FullPrivilegeList extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add update
    initValues: {},
  };

  componentDidMount() {
    const { dispatch, searchParams } = this.props;
    // 查询初始数据
    dispatch({
      type: 'fullPrivilegeList/page',
      payload: {
        ...searchParams,
        ...PaginationHelper.defaultPayload,
      },
    });
  }

  handleSortModalVisible = (sortModalVisible, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'fullPrivilegeList/setAll',
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
              <Button
                icon="plus"
                type="primary"
                onClick={() => alert('正在开发中')}
              >
                新建满减送
              </Button>
            </div>
          </div>
          <SearchForm {...searchFormProps} />
          <List {...listProps} />
        </Card>
      </PageHeaderWrapper>
    );
  }
}

export default FullPrivilegeList;
