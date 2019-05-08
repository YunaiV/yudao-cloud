import React from 'react';
import { Button, Col, Form, Input, Row, DatePicker } from 'antd';

const FormItem = Form.Item;

/**
 * table 查询
 *
 * @type {React.ComponentClass<RcBaseFormProps & Omit<FormComponentProps, keyof FormComponentProps>>}
 */
const TableSearch = Form.create()(props => {
  const { getFieldDecorator, form, handleSearch } = props.form;

  function onSubmit(e) {
    e.preventDefault();

    form.validateFields((err, fields) => {
      const buildTime = (fieldValue, key) => {
        const res = {};
        if (fieldValue && fieldValue.length >= 2) {
          const keySuffix = key.substring(0, 1).toUpperCase() + key.substring(1);
          res[`start${keySuffix}`] = fieldValue[0].format('YYYY-MM-DD HH:mm:ss');
          res[`end${keySuffix}`] = fieldValue[1].format('YYYY-MM-DD HH:mm:ss');
        }
        return res;
      };

      const timeFields = ['createTime'];
      const buildSearchParams = fields2 => {
        let res = {};
        Object.keys(fields).map(objectKey => {
          const fieldValue = fields2[objectKey];
          if (timeFields.indexOf(objectKey) !== -1) {
            // 处理时间
            res = {
              ...res,
              ...buildTime(fieldValue, objectKey),
            };
          } else if (fieldValue !== undefined) {
            res[objectKey] = fieldValue;
          }
          return true;
        });
        return res;
      };

      const searchParams = buildSearchParams(fields);
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
          <FormItem label="订单ID">
            {getFieldDecorator('id')(<Input placeholder="请输入订单ID" />)}
          </FormItem>
        </Col>
        <Col md={8} sm={24}>
          <FormItem label="订单号">
            {getFieldDecorator('orderNo')(<Input placeholder="请输入订单号" />)}
          </FormItem>
        </Col>
      </Row>

      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <FormItem label="创建时间">
            {getFieldDecorator('createTime')(<DatePicker.RangePicker />)}
          </FormItem>
        </Col>
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

export default TableSearch;
