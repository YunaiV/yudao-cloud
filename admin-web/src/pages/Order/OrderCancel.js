import React from 'react';
import { Form, Input, Modal } from 'antd';
import DictionarySelect from '@/components/Dictionary/DictionarySelect';
import dictionary from '@/utils/dictionary';

const FormItem = Form.Item;

// 订单 - 更新支付金额
const OrderCancel = Form.create()(props => {
  const { form, dispatch, loading } = props;
  const { orderId, orderCancelVisible, orderCancelShowOther, searchParams } = props.orderList;
  const { getFieldDecorator } = props.form;

  const handleOk = e => {
    e.preventDefault();
    form.validateFields((err, fields) => {
      if (err) return;
      dispatch({
        type: 'orderList/cancelOrder',
        payload: {
          params: {
            orderId,
            reasons: fields.reasons,
            otherReasons: fields.otherReasons,
          },
          searchParams,
        },
      });
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

  const handleReasonsChange = key => {
    dispatch({
      type: 'orderList/changeOrderCancelShowOther',
      payload: {
        orderCancelShowOther: key === '20',
      },
    });
  };

  return (
    <Modal
      destroyOnClose
      title="取消订单"
      visible={orderCancelVisible}
      onOk={handleOk}
      okText="保存"
      onCancel={handleCancel}
      confirmLoading={loading}
    >
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="原因">
        {getFieldDecorator('reasons', {
          rules: [{ required: true, message: '请选择取消原因！' }],
        })(
          <DictionarySelect
            onChange={handleReasonsChange}
            style={{ minWidth: '100%' }}
            dicKey={dictionary.ORDER_CANCEL_REASONS}
          />
        )}
      </FormItem>
      {orderCancelShowOther ? (
        <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="其他原因">
          {getFieldDecorator('otherReasons', {
            rules: [{ required: true, message: '请输填写退款原因！' }],
          })(<Input.TextArea autosize={{ minRows: 3, maxRows: 6 }} placeholder="请输入退款原因" />)}
        </FormItem>
      ) : (
        ''
      )}
    </Modal>
  );
});

export default OrderCancel;
