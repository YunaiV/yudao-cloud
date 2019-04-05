import React from 'react';
import { Table, Modal, Card, Form, Input, message } from 'antd';
import DictionaryText from '@/components/Dictionary/DictionaryText';
import DictionarySelect from '@/components/Dictionary/DictionarySelect';
import dictionary from '@/utils/dictionary';
import styles from './OrderDelivery.less';

const OrderDelivery = Form.create()(props => {
  const columns = [
    {
      title: '商品',
      dataIndex: 'skuName',
      render: (text, row) => {
        return (
          <div>
            <img className={styles.goodImg} alt={row.skuName} src={row.skuImage} />
            <span>{row.skuName}</span>
          </div>
        );
      },
    },
    {
      title: '数量',
      dataIndex: 'quantity',
      render: quantity => <span>{quantity}</span>,
    },
    {
      title: '状态',
      dataIndex: 'status',
      sorter: true,
      render: status => <DictionaryText dicKey={dictionary.ORDER_STATUS} dicValue={status} />,
    },
    {
      title: '运输号',
      dataIndex: 'orderLogisticsId',
      width: 200,
      render: orderLogisticsId => {
        return <span>{orderLogisticsId || '-'}</span>;
      },
    },
  ];

  const handleCancel = () => {
    const { dispatch } = props;
    dispatch({
      type: 'orderDelivery/changeVisible',
      payload: {
        visible: false,
      },
    });
  };

  const handleOk = e => {
    e.preventDefault();
    const { dispatch, form } = props;
    const { selectedRowKeys, orderId } = props.orderDelivery;
    form.validateFields((err, fields) => {
      if (err) return;
      console.log('fields', fields);

      console.log('selectedRowKeys', selectedRowKeys);
      if (selectedRowKeys.length <= 0) {
        message.error('至少选择一个发货的商品！');
      } else {
        dispatch({
          type: 'orderDelivery/deliver',
          payload: {
            ...fields,
            orderId,
            orderItemIds: selectedRowKeys,
          },
        });
      }
    });
  };

  const { loading, orderDelivery } = props;
  const { getFieldDecorator } = props.form;
  const { list, visible, orderRecipient } = orderDelivery;
  const { name, mobile, address } = orderRecipient || {};

  // rowSelection objects indicates the need for row selection
  const rowSelection = {
    onChange: (selectedRowKeys, selectedRows) => {
      console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
      props.dispatch({
        type: 'orderDelivery/changeSelectedRowKeys',
        payload: {
          selectedRowKeys,
        },
      });
    },
    onSelect: (record, selected, selectedRows) => {
      console.log(record, selected, selectedRows);
    },
    onSelectAll: (selected, selectedRows, changeRows) => {
      console.log(selected, selectedRows, changeRows);
    },
  };

  return (
    <Modal
      destroyOnClose
      title="发货"
      visible={visible}
      onOk={handleOk}
      okText="保存"
      onCancel={handleCancel}
      confirmLoading={loading}
      width={800}
    >
      <Table
        rowKey="id"
        columns={columns}
        dataSource={list}
        loading={loading}
        pagination={false}
        rowSelection={rowSelection}
        // onSelectRow={handleSelectRows}
        // onChange={handleStandardTableChange}
      />

      <Card loading={loading}>
        <div>
          <h3>配送信息</h3>{' '}
        </div>
        <div>
          收货人: {name} ({mobile})
        </div>
        <div>配件方式: 快递 TODO暂时只有一种</div>
        <div>收件地址: {address}</div>
      </Card>

      <Card loading={loading}>
        <div>
          <h3>发货方式</h3>{' '}
        </div>
        <Form>
          <Form.Item label="物流">
            {getFieldDecorator('logistics', {
              rules: [{ required: true, message: '必选!' }],
            })(
              <DictionarySelect
                style={{ minWidth: '100%' }}
                dicKey={dictionary.LOGISTICS_COMPANY}
              />
            )}
          </Form.Item>
          <Form.Item label="快递号">
            {getFieldDecorator('logisticsNo', {
              rules: [{ required: true, message: '必选!' }],
            })(<Input placeholder="请输入快递号." />)}
          </Form.Item>
          <Form.Item>
            *请仔细填写物流公司及快递单号，发货后24小时内仅支持做一次更正，逾期不可修改
          </Form.Item>
        </Form>
      </Card>
    </Modal>
  );
});

export default OrderDelivery;
