import React from 'react';
import { Form, Input, Modal } from 'antd';
import DictionarySelect from '../../components/Dictionary/DictionarySelect';
import dictionary from '../../utils/dictionary';

/**
 * table 查询
 *
 * @type {React.ComponentClass<RcBaseFormProps & Omit<FormComponentProps, keyof FormComponentProps>>}
 */
const SignListUpdate = Form.create()(props => {
  const { onOk, onCancel, visible, title, form, initData = {} } = props;
  const { getFieldDecorator, validateFields } = props.form;

  function handleOk(e) {
    e.preventDefault();

    validateFields((err, fields) => {
      const searchParams = fields;
      if (onOk) {
        onOk(searchParams);
        form.resetFields();
      }
    });
  }

  function handleCancel() {
    if (onCancel) {
      onCancel();
    }
  }

  const formItemLayout = {
    labelCol: { span: 4 },
    wrapperCol: { span: 18 },
  };

  return (
    <Modal title={title} visible={visible} onOk={handleOk} onCancel={handleCancel}>
      <Form>
        <Form.Item {...formItemLayout} label="签名">
          {getFieldDecorator('sign', {
            rules: [
              {
                required: true,
                message: '请输入签名',
              },
            ],
            initialValue: initData.sign,
          })(<Input placeholder="请输入签名" />)}
        </Form.Item>
        <Form.Item {...formItemLayout} label="平台">
          {getFieldDecorator('platform', {
            rules: [
              {
                required: true,
                message: '请选择平台',
              },
            ],
            initialValue: `${initData.platform}`,
          })(<DictionarySelect dicKey={dictionary.SMS_PLATFORM} />)}
        </Form.Item>
      </Form>
    </Modal>
  );
});

export default SignListUpdate;
