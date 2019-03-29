import React from 'react';
import { Form, Input, Modal } from 'antd';

const FormItem = Form.Item;

// 订单 - 更新支付金额
const OrderUpdatePayAmount = Form.create()(props => {
  const { dispatch, loading } = props;
  const { orderId, orderItemId, payAmount, payAmountVisible, searchParams } = props.orderList;
  const { getFieldDecorator, getFieldsValue } = props.form;

  const handleOk = e => {
    e.preventDefault();
    const fieldsValue = getFieldsValue();

    dispatch({
      type: 'orderList/updatePayAmount',
      payload: {
        params: {
          payAmount: fieldsValue.payAmount * 100,
          orderId,
          orderItemId,
        },
        searchParams,
      },
    });
  };

  const handleCancel = () => {
    dispatch({
      type: 'orderList/changePayAmountVisible',
      payload: {
        payAmountVisible: false,
      },
    });
  };

  return (
    <Modal
      destroyOnClose
      title="修改实付金额"
      visible={payAmountVisible}
      onOk={handleOk}
      okText="保存"
      onCancel={handleCancel}
      confirmLoading={loading}
    >
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="用户名">
        {getFieldDecorator('payAmount', {
          rules: [
            { required: true, message: '请输入需要修改的金额！' },
            { max: 10000, min: 0, message: '金额值 0 - 100000 元' },
          ],
          initialValue: payAmount / 100,
        })(<Input placeholder="请输入修改的金额" />)}
      </FormItem>
    </Modal>
  );
});

export default OrderUpdatePayAmount;
