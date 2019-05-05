// import React from 'react';
// import { Button, Col, Form, Input, Row, DatePicker } from 'antd';
//
// const FormItem = Form.Item;
//
// /**
//  * table 查询
//  *
//  * @type {React.ComponentClass<RcBaseFormProps & Omit<FormComponentProps, keyof FormComponentProps>>}
//  */
// const TableSearch = Form.create()(props => {
//   const { getFieldDecorator } = props.form;
//   console.log('props.form', props.form);
//
//   function onSubmit() {}
//
//   function handleFormReset() {}
//
//   return (
//     <Form onSubmit={onSubmit} layout="inline">
//       <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
//         <Col md={8} sm={24}>
//           <FormItem label="订单id">
//             {getFieldDecorator('id')(<Input placeholder="请输入订单id" />)}
//           </FormItem>
//         </Col>
//         <Col md={8} sm={24}>
//           <FormItem label="订单号">
//             {getFieldDecorator('orderNo')(<Input placeholder="请输入订单号" />)}
//           </FormItem>
//         </Col>
//       </Row>
//
//       <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
//         <Col md={8} sm={24}>
//           <FormItem label="创建时间">
//             {getFieldDecorator('createTime')(<DatePicker.RangePicker />)}
//           </FormItem>
//         </Col>
//         <Col md={8} sm={24}>
//           <span>
//             <Button type="primary" htmlType="submit">
//               查询
//             </Button>
//             <Button style={{ marginLeft: 8 }} onClick={handleFormReset}>
//               重置
//             </Button>
//           </span>
//         </Col>
//       </Row>
//     </Form>
//   );
// });
//
// export default TableSearch;
