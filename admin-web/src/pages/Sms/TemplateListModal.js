import React from 'react';
import { Form, Input, Modal, Select } from 'antd';
import DictionarySelect from '../../components/Dictionary/DictionarySelect';
import dictionary from '../../utils/dictionary';

/**
 * table 查询
 *
 * @type {React.ComponentClass<RcBaseFormProps & Omit<FormComponentProps, keyof FormComponentProps>>}
 */
const SignListModal = Form.create()(props => {
  const { onOk, onCancel, visible, title, form, signList, initData = {} } = props;
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
          {getFieldDecorator('smsSignId', {
            rules: [
              {
                required: true,
                message: '请输入签名',
              },
            ],
            initialValue: initData.sign ? initData.sign.id : null,
          })(
            <Select>
              {signList.map(item => (
                <Select.Option key={item.id} value={item.id}>
                  {item.sign}
                </Select.Option>
              ))}
            </Select>
          )}
        </Form.Item>
        <Form.Item {...formItemLayout} label="类型">
          {getFieldDecorator('smsType', {
            rules: [
              {
                required: true,
                message: '请选择短信类型',
              },
            ],
            initialValue: initData.smsType,
          })(<DictionarySelect placeholder="请选择短信类型" dicKey={dictionary.SMS_TYPE} />)}
        </Form.Item>
        <Form.Item {...formItemLayout} label="平台">
          {getFieldDecorator('platform', {
            rules: [
              {
                required: true,
                message: '请选择平台',
              },
            ],
            initialValue: initData.platform,
          })(<DictionarySelect placeholder="请选择平台" dicKey={dictionary.SMS_PLATFORM} />)}
        </Form.Item>
        <Form.Item {...formItemLayout} label="模板Code">
          {getFieldDecorator('templateCode', {
            rules: [
              {
                required: true,
                message: '短信平台的Code',
              },
            ],
            initialValue: initData.templateCode,
          })(<Input placeholder="第三方平台Code" />)}
        </Form.Item>
        <Form.Item {...formItemLayout} label="模板">
          {getFieldDecorator('template', {
            rules: [
              {
                required: true,
                message: '请输入短信模板',
              },
            ],
            initialValue: initData.template,
          })(<Input.TextArea placeholder="请输入模板" rows={4} />)}
        </Form.Item>
      </Form>
    </Modal>
  );
});

export default SignListModal;
