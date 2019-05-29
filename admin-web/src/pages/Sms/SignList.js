import React, { PureComponent } from 'react';
import { Card, Divider, Table, Modal, Button } from 'antd';
import { connect } from 'dva';

import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import DictionaryText from '../../components/Dictionary/DictionaryText';
import dictionary from '../../utils/dictionary';
import styles from '../List/TableList.less';
import SignListSearch from './SignListSearch';
import SignListModal from './SignListModal';

@connect(({ smsSignList, loading }) => ({
  smsSignList,
  loading: loading.models.smsSignList,
}))
class SignList extends PureComponent {
  state = {
    visible: false,
    title: '添加签名', // 添加签名 修改签名
    type: 'add', // add update
    id: '',
    sign: {},
  };

  componentDidMount() {
    // init page 参数
    this.current = 1;
    this.total = 0;
    this.size = 10;
    this.searchParams = {};

    // 查询 page
    this.queryPage();
  }

  queryPage = () => {
    const { dispatch } = this.props;
    const { current, total, size, searchParams } = this;

    dispatch({
      type: 'smsSignList/page',
      payload: {
        current,
        total,
        size,
        ...searchParams,
      },
    });
  };

  handleSearch = searchParams => {
    this.searchParams = searchParams;
    this.queryPage();
  };

  handleAddShow = () => {
    this.setState({
      visible: true,
      type: 'add',
      title: '添加签名',
      sign: {},
    });
  };

  handleUpdateShow = sign => {
    const { id } = sign;
    this.setState({
      visible: true,
      type: 'update',
      title: '更新签名',
      id,
      sign,
    });
  };

  handleDeleted = ({ id, sign }) => {
    const { dispatch } = this.props;
    Modal.confirm({
      title: `提示消息`,
      content: `确认删除 ${sign} 签名吗`,
      okText: '确认',
      cancelText: '取消',
      onOk: () => {
        dispatch({
          type: 'smsSignList/deleted',
          payload: {
            params: {
              id,
            },
            callback: () => {
              this.queryPage();
            },
          },
        });
      },
      onCancel() {
        console.log('Cancel');
      },
    });
  };

  handleCancel = () => {
    this.setState({
      visible: false,
    });
  };

  handleOk = fields => {
    const { dispatch } = this.props;
    const { type, id } = this.state;

    if (type === 'add') {
      dispatch({
        type: 'smsSignList/add',
        payload: {
          params: {
            ...fields,
          },
          callback: () => {
            this.handleCancel();
            this.queryPage();
          },
        },
      });
    } else if (type === 'update') {
      dispatch({
        type: 'smsSignList/update',
        payload: {
          params: {
            id,
            ...fields,
          },
          callback: () => {
            this.handleCancel();
            this.queryPage();
          },
        },
      });
    }
  };

  handleTableChange = pagination => {
    const { pageSize, current } = pagination;
    this.size = pageSize;
    this.current = current;
    this.queryPage();
  };

  render() {
    // props
    const { loading, smsSignList } = this.props;
    const { list, total, index, size } = smsSignList;
    const { visible, title, type, sign } = this.state;

    const columns = [
      {
        title: 'ID',
        dataIndex: 'id',
        key: 'id',
      },
      {
        title: '签名',
        dataIndex: 'sign',
        key: 'sign',
      },
      {
        title: '短信平台',
        dataIndex: 'platform',
        key: 'platform',
        render(platform) {
          return (
            <div>
              <DictionaryText dicKey={dictionary.SMS_PLATFORM} dicValue={platform} />
            </div>
          );
        },
      },
      {
        title: '审核状态',
        dataIndex: 'applyStatus',
        key: 'applyStatus',
        render(applyStatus) {
          return (
            <div>
              <DictionaryText dicKey={dictionary.SMS_APPLY_STATUS} dicValue={applyStatus} />
            </div>
          );
        },
      },
      {
        title: '更新时间',
        dataIndex: 'updateTime',
        key: 'updateTime',
        width: 200,
        render(updateTime) {
          return <div>{updateTime}</div>;
        },
      },
      {
        title: '创建时间',
        dataIndex: 'createTime',
        key: 'createTime',
        width: 200,
        render(createTime) {
          return <div>{createTime}</div>;
        },
      },
      {
        title: '操作',
        render: row => {
          return (
            <div>
              <a onClick={this.handleUpdateShow.bind(this, row)}>修改</a>
              <Divider type="vertical" />
              <a onClick={this.handleDeleted.bind(this, row)}>删除</a>
            </div>
          );
        },
      },
    ];

    const pagination = {
      total,
      index,
      pageSize: size,
    };

    return (
      <PageHeaderWrapper>
        <Card>
          <div className={styles.tableListForm}>
            <SignListSearch handleSearch={this.handleSearch} />
          </div>
          <br />
          <div>
            <Button icon="plus" onClick={this.handleAddShow}>
              添加签名
            </Button>
          </div>
          <br />

          <Table
            loading={loading}
            rowKey="id"
            dataSource={list}
            columns={columns}
            pagination={pagination}
            onChange={this.handleTableChange}
          />
        </Card>

        <SignListModal
          initData={sign}
          title={title}
          visible={visible}
          type={type}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        />
      </PageHeaderWrapper>
    );
  }
}

export default SignList;
