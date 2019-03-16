/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import {Card, Form, Input, Radio, Button, Table, Divider} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './ProductSpuAddOrUpdate.less';

const FormItem = Form.Item;
const RadioGroup = Radio.Group;

// roleList
@connect(({ productSpuList, loading }) => ({
  productSpuList,
  list: productSpuList.list.spus,
  loading: loading.models.productSpuList,
}))

@Form.create()
class ProductSpuAddOrUpdate extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add update
    initValues: {},
    roleAssignVisible: false,
    roleAssignRecord: {},
  };

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'productSpuList/page',
      payload: {
        name: '',
        pageNo: 0,
        pageSize: 10,
      },
    });
  }

  handleSubmit = e => {
    const { dispatch, form } = this.props;
    e.preventDefault();
    form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        dispatch({
          type: 'form/submitRegularForm',
          payload: values,
        });
      }
    });
  }

  render() {
    // debugger;
    const { form, data } = this.props;

    // 规格明细
    const columns = [
      {
        title: '颜色',
        dataIndex: 'price'
      },
      {
        title: '价格',
        dataIndex: 'price',
        render(val) {
          return <span>{status[val]}</span>;
        },
      },
      {
        title: '库存',
        dataIndex: 'quantity',
      }
    ];

    return (
      <PageHeaderWrapper title="">
        <Card bordered={false}>
          <Form onSubmit={this.handleSubmit} hideRequiredMark style={{ marginTop: 8 }}>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="商品名">
              {form.getFieldDecorator('name', {
                rules: [{ required: true, message: '请输入商品名！', min: 2 }],
                initialValue: '', // TODO 修改
              })(<Input placeholder="请输入" />)}
            </FormItem>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="商品卖点">
              {form.getFieldDecorator('sellPoint', {
                rules: [{ required: true, message: '请输入商品卖点！' }],
                initialValue: '', // TODO 修改
              })(<Input placeholder="请输入" />)}
            </FormItem>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="商品描述">
              {form.getFieldDecorator('description', {
                rules: [{ required: true, message: '请输入商品描述！' }],
                initialValue: '', // TODO 修改
              })(<Input.TextArea placeholder="请输入" />)}
            </FormItem>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="分类编号">
              {form.getFieldDecorator('cid', {
                rules: [{ required: true, message: '请输入分类编号！' }],
                initialValue: '', // TODO 修改 // TODO 芋艿，和面做成下拉框
              })(<Input placeholder="请输入" />)}
            </FormItem>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="商品主图">
              {form.getFieldDecorator('picUrls', {
                initialValue: '', // TODO 修改 // TODO 芋艿，做成上传组件
              })(<Input placeholder="请输入" />)}
            </FormItem>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="是否上架">
              {form.getFieldDecorator('visible', {
                initialValue: 1, // TODO 修改
              })(
                <RadioGroup>
                  <Radio value={1}>是</Radio>
                  <Radio value={2}>否</Radio>
                </RadioGroup>
              )}
            </FormItem>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="商品规格">
              {form.getFieldDecorator('visible', {
                initialValue: 1, // TODO 修改
              })(
                <Button>添加规格项目</Button>
              )}
            </FormItem>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="规格明细">
              {form.getFieldDecorator('visible', {
                initialValue: 1, // TODO 修改
              })(
                <Table defaultExpandAllRows={true} columns={columns} rowKey="id" />
              )}
            </FormItem>
          </Form>
        </Card>
      </PageHeaderWrapper>
    );
  }
}

export default ProductSpuAddOrUpdate;
