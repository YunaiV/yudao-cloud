import React from 'react';
import { Button, Col, Form, Input, Row } from 'antd';

const FormItem = Form.Item;

/**
 * table 查询
 *
 * @type {React.ComponentClass<RcBaseFormProps & Omit<FormComponentProps, keyof FormComponentProps>>}
 */
const SignListSearch = Form.create()(props => {
  const { handleSearch } = props;
  const { getFieldDecorator, validateFields, form } = props.form;

  function onSubmit(e) {
    e.preventDefault();

    validateFields((err, fields) => {
      const searchParams = fields;
      if (handleSearch) {
        handleSearch(searchParams);
      }
    });
  }

  function handleFormReset() {
    form.resetFields();
  }

  return (
    <Form onSubmit={onSubmit} layout="inline">
      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <FormItem label="编号">
            {getFieldDecorator('id')(<Input placeholder="请输入ID" />)}
          </FormItem>
        </Col>
        <Col md={8} sm={24}>
          <FormItem label="签名">
            {getFieldDecorator('sign')(<Input placeholder="请输入签名" />)}
          </FormItem>
        </Col>
      </Row>

      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <span>
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
});

export default SignListSearch;
