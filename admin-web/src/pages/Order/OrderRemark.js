import React from 'react';
import { Form, Input, Modal } from 'antd';

const FormItem = Form.Item;

// 订单 - 更新支付金额
const OrderRemark = Form.create()(props => {
  const { dispatch, loading } = props;
  const { orderId, remark, remarkVisible, searchParams } = props.orderList;
  const { getFieldDecorator, getFieldsValue } = props.form;

  const handleOk = e => {
    e.preventDefault();
    const fieldsValue = getFieldsValue();

    dispatch({
      type: 'orderList/updateRemake',
      payload: {
        params: {
          remark: fieldsValue.remark,
          orderId,
        },
        searchParams,
      },
    });
  };

  const handleCancel = () => {
    dispatch({
      type: 'orderList/changeRemakeVisible',
      payload: {
        remarkVisible: false,
      },
    });
  };

  return (
    <Modal
      destroyOnClose
      title="添加备注信息"
      visible={remarkVisible}
      onOk={handleOk}
      okText="保存"
      onCancel={handleCancel}
      confirmLoading={loading}
    >
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="备注">
        {getFieldDecorator('remark', {
          initialValue: remark,
        })(<Input.TextArea autosize={{ minRows: 2, maxRows: 6 }} placeholder="请输入备注信息" />)}
      </FormItem>
    </Modal>
  );
});

export default OrderRemark;
