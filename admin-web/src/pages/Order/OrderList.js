import React, { PureComponent } from 'react';
import moment from 'moment';
import { connect } from 'dva';
import {
  Button,
  Card,
  Col,
  Dropdown,
  Form,
  Icon,
  Input,
  List,
  Menu,
  Modal,
  Row,
  Select,
} from 'antd';

import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import DictionaryText from '@/components/Dictionary/DictionaryText';
import dictionary from '@/utils/dictionary';

import styles from './OrderList.less';

const FormItem = Form.Item;
const SelectOption = Select.Option;

const OrderList = props => {
  const { list, loading } = props;

  const paginationProps = {
    showSizeChanger: true,
    showQuickJumper: true,
    pageSize: 5,
    total: 50,
  };

  const deleteItem = id => {
    const { dispatch } = props;
    dispatch({
      type: 'list/submit',
      payload: { id },
    });
  };

  const handleEditor = currentItem => {
    const { handleEditorClick } = props;
    if (handleEditorClick) {
      handleEditorClick(currentItem);
    }
  };

  const handleMoreMenu = (key, currentItem) => {
    if (key === 'edit') {
      handleEditor(currentItem);
    } else if (key === 'delete') {
      Modal.confirm({
        title: '删除任务',
        content: '确定删除该任务吗？',
        okText: '确认',
        cancelText: '取消',
        onOk: () => deleteItem(currentItem.id),
      });
    }
  };

  const ListContent = ({ data }) => (
    <div className={styles.listContent}>
      <div className={styles.listContentItem}>
        <span>金额: {data.price / 100} 元</span>
        <p>编号: {data.orderNo}</p>
      </div>
      <div className={styles.listContentItem}>
        <span>
          付款时间: {data.paymentTime ? moment(data.paymentTime).format('YYYY-MM-DD HH:mm') : ''}
        </span>
        <p>创建时间：{moment(data.createTime).format('YYYY-MM-DD HH:mm')}</p>
      </div>
      <div className={styles.listContentItem}>
        <span>
          订单状态: <DictionaryText dicKey={dictionary.ORDER_STATUS} dicValue={data.status} />
        </span>
      </div>
    </div>
  );

  const MoreBtn = () => (
    <Dropdown
      overlay={
        <Menu onClick={({ key }) => handleMoreMenu(key, props.current)}>
          <Menu.Item key="edit">编辑</Menu.Item>
          <Menu.Item key="delete">删除</Menu.Item>
        </Menu>
      }
    >
      <a>
        更多 <Icon type="down" />
      </a>
    </Dropdown>
  );

  return (
    <List
      size="large"
      rowKey="id"
      loading={loading}
      pagination={paginationProps}
      dataSource={list}
      renderItem={item => (
        <List.Item
          actions={[
            <a
              onClick={e => {
                e.preventDefault();
                handleEditor(item);
              }}
            >
              编辑
            </a>,
            <MoreBtn current={item} />,
          ]}
        >
          <ListContent data={item} />
        </List.Item>
      )}
    />
  );
};

// SearchForm
const SearchForm = props => {
  const {
    form: { getFieldDecorator },
  } = props;

  const handleFormReset = () => {};

  const handleSearch = () => {};

  return (
    <Form onSubmit={handleSearch} layout="inline">
      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <FormItem label="规则名称">
            {getFieldDecorator('name')(<Input placeholder="请输入" />)}
          </FormItem>
        </Col>
        <Col md={8} sm={24}>
          <FormItem label="使用状态">
            {getFieldDecorator('status')(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                <SelectOption value="0">关闭</SelectOption>
                <SelectOption value="1">运行中</SelectOption>
              </Select>
            )}
          </FormItem>
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
  list: orderList.list,
  orderList,
  loading: loading.models.orderList,
}))
@Form.create()
class BasicList extends PureComponent {
  state = {
    current: {},
  };

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'orderList/queryPage',
      payload: {
        pageNo: 0,
        pageSize: 10,
      },
    });
  }

  handleEditorClick = () => {
    console.info('edit');
  };

  handleSubmit = e => {
    e.preventDefault();
    const { dispatch, form } = this.props;
    const { current } = this.state;
    const id = current ? current.id : '';

    form.validateFields((err, fieldsValue) => {
      if (err) return;
      dispatch({
        type: 'list/submit',
        payload: { id, ...fieldsValue },
      });
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
              <SearchForm {...this.props} />
            </div>
            <OrderList {...this.props} handleEditorClick={this.handleEditorClick} />
          </Card>
        </div>
      </PageHeaderWrapper>
    );
  }
}

export default BasicList;
