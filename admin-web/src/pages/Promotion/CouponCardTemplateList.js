/* eslint-disable */

import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import {
  Card,
  Form,
  Input,
  Button,
  Modal,
  message,
  Table,
  Divider,
  Tree,
  Spin,
  Row,
  Col,
  Select,
  Icon,
  InputNumber,
  DatePicker
} from 'antd';
import { checkTypeWithEnglishAndNumbers } from '../../../helpers/validator'
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import styles from './CouponCardTemplateList.less';
import moment from "moment";
import PaginationHelper from "../../../helpers/PaginationHelper";

const FormItem = Form.Item;
const SelectOption = Select.Option;
const { TreeNode } = Tree;
const RangePicker = DatePicker.RangePicker;
const status = ['未知', '正常', '禁用'];
const rangeType = {
  10: '所有可用',
  20: '部分商品可用',
  21: '部分商品不可用',
  30: '部分分类可用',
  31: '部分分类不可用'};
const preferentialType = ['未知', '代金卷', '折扣卷'];
const dateType = ['未知', '固定日期', '领取日期'];

// 列表
function List ({ dataSource, loading, pagination, searchParams, dispatch,
                 handleModalVisible}) {

  function handleStatus(record) {
    Modal.confirm({
      title: record.status === 1 ? '确认禁用' : '取消禁用',
      content: `${record.productSpuId}`,
      onOk() {
        dispatch({
          type: 'couponCardTemplateList/updateStatus',
          payload: {
            id: record.id,
            status: record.status === 1 ? 2 : 1,
          },
        });
      },
      onCancel() {},
    });
  }

  // function handleDelete(record) {
  //   Modal.confirm({
  //     title: `确认删除?`,
  //     content: `${record.productSpuId}`,
  //     onOk() {
  //       dispatch({
  //         type: 'couponCardTemplateList/delete',
  //         payload: {
  //           id: record.id,
  //         },
  //       });
  //     },
  //     onCancel() {},
  //   });
  // }

  const columns = [
    {
      title: '名称',
      dataIndex: 'title',
    },
    {
      title: '类型',
      dataIndex: 'preferentialType',
      render(val) {
        return <span>{preferentialType[val]}</span>;
      },
    },
    {
      title: '优惠内容',
      render(val, record) {
        let content;
        // priceAvailable;
        if (record.priceAvailable === 0) {
          content = '无门槛,';
        } else {
          content = '满 ' + record.priceAvailable / 100 + ' 元,';
        }
        if (record.preferentialType === 1) {
          content += '减 ' + record.priceOff / 100 + ' 元';
        } else {
          content += '打' + record.percentOff / 100.0 + '折';
          if (record.discountPriceLimit) {
            content += ', 最多减 ' + record.discountPriceLimit / 100 + ' 元';
          }
        }
        return content;
      }
    },
    {
      title: '可使用商品',
      dataIndex: 'rangeType',
      render: val => <span>{rangeType[val]}</span>,
    },
    {
      title: '有效期',
      render(val, record) {
        let content = dateType[record.dateType] + ' ';
        // priceAvailable;
        if (record.dateType === 1) {
          content += moment(new Date(record.validStartTime)).format('YYYY-MM-DD')
            + '~' +  moment(new Date(record.validEndTime)).format('YYYY-MM-DD');
        } else if (record.dateType === 2) {
          content += record.fixedStartTerm + '-' + record.fixedEndTerm + ' 天';
        }
        return content;
      }
    },
    {
      title: '已领取/剩余',
      // 已使用 TODO 芋艿
      // 支付金额(元) TODO 芋艿
      // 客单价(元) TODO 芋艿
      render(val, record) {
        return `${record.statFetchNum} / ` + (record.total - record.statFetchNum);
      }
    },
    {
      title: '状态',
      dataIndex: 'status',
      render: val => <span>{status[val]}</span>,
    },
    {
      title: '使用说明',
      dataIndex: 'description',
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      render: val => <span>{moment(val).format('YYYY-MM-DD HH:mm')}</span>,
    },
    {
      title: '操作',
      width: 120,
      render: (text, record) => {
        const statusText = record.status === 1 ? '禁用' : '开启'; // TODO 芋艿，此处要改
        return (
          <Fragment>
            <a onClick={() => handleModalVisible(true, 'update', record)}>编辑</a>
            <Divider type="vertical" />
            <a className={styles.tableDelete} onClick={() => handleStatus(record)}>
              {statusText}
            </a>
            {/*{*/}
            {/*  record.status === 2 ?*/}
            {/*    <span>*/}
            {/*      <Divider type="vertical" />*/}
            {/*      <a className={styles.tableDelete} onClick={() => handleDelete(record)}>*/}
            {/*        删除*/}
            {/*      </a>*/}
            {/*    </span> : null*/}
            {/*}*/}
          </Fragment>
        );
      },
    },
  ];

  function onPageChange(page) { // 翻页
    dispatch({
      type: 'couponCardTemplateList/query',
      payload: {
        pageNo: page.current,
        pageSize: page.pageSize,
        ...searchParams
      }
    })
  }

  return (
    <Table
      columns={columns}
      dataSource={dataSource}
      loading={loading}
      rowKey="id"
      pagination={pagination}
      onChange={onPageChange}
    />
  )
}

// 搜索表单
// TODO 芋艿，有没办法换成上面那种写法
const SearchForm = Form.create()(props => {
  const {
    form,
    form: { getFieldDecorator },
    dispatch
  } = props;

  function search() {
    dispatch({
      type: 'couponCardTemplateList/query',
      payload: {
        ...PaginationHelper.defaultPayload,
        ...form.getFieldsValue()
      }
    })
  }

  // 提交搜索
  function handleSubmit(e) {
    // 阻止默认事件
    e.preventDefault();
    // 提交搜索
    search();
  }

  // 重置搜索
  function handleReset() {
    // 重置表单
    form.resetFields();
    // 执行搜索
    search();
  }

  return (
    <Form onSubmit={handleSubmit} layout="inline">
      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <FormItem label="标题">
            {getFieldDecorator('title')(<Input placeholder="请输入" />)}
          </FormItem>
        </Col>
        <Col md={8} sm={24}>
            <span className={styles.submitButtons}>
              <Button type="primary" htmlType="submit">
                查询
              </Button>
              <Button style={{ marginLeft: 8 }} onClick={handleReset}>
                重置
              </Button>
            </span>
        </Col>
      </Row>
    </Form>
  );
});

// 添加 or 修改 Form 表单
const AddOrUpdateForm = Form.create()(props => {
  const { dispatch, modalVisible, form, handleModalVisible, modalType, formVals } = props;

  const okHandle = () => {
    form.validateFields((err, fields) => {
      if (err) return;
      let newFileds = {
        ...fields,
        priceAvailable: fields.priceAvailable ? parseInt(fields.priceAvailable * 100) : undefined,
        priceOff: fields.priceOff ? parseInt(fields.priceOff * 100) : undefined,
        discountPriceLimit: fields.discountPriceLimit ? parseInt(fields.discountPriceLimit * 100) : undefined,
        validStartTime: fields.validStartTime ? fields.validStartTime.format('YYYY-MM-DD') : undefined,
        validEndTime: fields.validEndTime ? fields.validEndTime.format('YYYY-MM-DD') : undefined
      };
      // 添加表单
      if (modalType === 'add') {
        dispatch({
          type: 'couponCardTemplateList/add',
          payload: {
            body: {
              ...newFileds,
            },
            callback: () => {
              // 清空表单
              form.resetFields();
              // 提示
              message.success('添加成功');
              // 关闭弹窗
              handleModalVisible();
            },
          },
        });
        // 修改表单
      } else {
        dispatch({
          type: 'couponCardTemplateList/update',
          payload: {
            body: {
              id: formVals.id,
              ...newFileds,
            },
            callback: () => {
              // 清空表单
              form.resetFields();
              // 提示
              message.success('更新成功');
              // 关闭弹窗
              handleModalVisible();
            },
          },
        });
      }
    });
  };

  function onRangeTypeChange(value) {
    formVals.rangeType = parseInt(value);
  }

  function onDateTypeChange(value) {
    formVals.dateType = parseInt(value);
  }

  function onPreferentialTypeChange(value) {
    formVals.preferentialType = parseInt(value);
  }

  const title = modalType === 'add' ? '新建优惠劵' : '更新优惠劵';
  return (
    <Modal
      destroyOnClose
      title={title}
      visible={modalVisible}
      onOk={okHandle}
      okText='保存'
      onCancel={() => handleModalVisible()}
      width={720}
    >
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="标题">
        {form.getFieldDecorator('title', {
          rules: [{ required: true, message: '请输入标题！' },
            {max: 16, min:2, message: '长度为 2-16 位'},],
          initialValue: formVals.title,
        })(<Input placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="使用说明">
        {form.getFieldDecorator('description', {
          rules: [{ required: false, message: '请输入使用说明！' },
            {max: 255, message: '最大长度为 255 位'},
          ],
          initialValue: formVals.description,
        })(<Input.TextArea placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="限领次数">
        {form.getFieldDecorator('quota', {
          rules: [{ required: true, message: '请选择每人限领次数！'},
          ],
          initialValue: formVals.quota,
        })(
          <Select placeholder="请选择" style={{ maxWidth: 200, width: '100%' }}>
            <SelectOption value="1">1 次</SelectOption>
            <SelectOption value="2">2 次</SelectOption>
            <SelectOption value="3">3 次</SelectOption>
            <SelectOption value="4">4 次</SelectOption>
            <SelectOption value="5">5 次</SelectOption>
            <SelectOption value="10">10 次</SelectOption>
          </Select>
        )}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="发放总量">
        {form.getFieldDecorator('total', {
          rules: [{ required: true, message: '请输入发放总量！' },
            {min: 1, type: 'number', message: '最小值为 1'}],
          initialValue: formVals.total,
        })(<InputNumber placeholder="请输入" />)}
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="使用金额门槛">
        {form.getFieldDecorator('priceAvailable', {
          rules: [{ required: true, message: '请输入使用金额门槛！' },],
          initialValue: formVals.priceAvailable,
        })(<InputNumber placeholder="请输入" />)} 元
      </FormItem>
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="可用范围">
        {form.getFieldDecorator('rangeType', {
          rules: [{ required: true, message: '请选择可用范围！'}, // TODO 芋艿，需要修改
          ],
          initialValue: formVals.rangeType,
        })(
          <Select placeholder="请选择" style={{ maxWidth: 200, width: '100%' }} onChange={onRangeTypeChange} >
            <SelectOption value="10">所有可用</SelectOption>
            <SelectOption value="20">部分商品可用</SelectOption>
            <SelectOption value="21">部分商品不可用</SelectOption>
            <SelectOption value="30">部分分类可用</SelectOption>
            <SelectOption value="31">部分分类不可用</SelectOption>
          </Select>
        )}
      </FormItem>
      {
        formVals.rangeType == 20 || formVals.rangeType == 21
        || formVals.rangeType == 30 || formVals.rangeType == 31 ?
        <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="具体范围">
            {form.getFieldDecorator('rangeValues', {
              rules: [{ required: true, message: '请输入具体范围！' }, // TODO 芋艿，做成搜索
                {maxlength: 255, message: '最大长度为 255 位'},
              ],
              initialValue: formVals.rangeValues,
            })(<Input.TextArea placeholder="请输入" />)}
          </FormItem>
        : ''
      }
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="生效日期类型">
        {form.getFieldDecorator('dateType', {
          rules: [{ required: true, message: '请选择可用范围！'}, // TODO 芋艿，需要修改
          ],
          initialValue: formVals.dateType,
        })(
          <Select placeholder="请选择" style={{ maxWidth: 200, width: '100%' }} onChange={onDateTypeChange}>
            <SelectOption value="1"></SelectOption>
            <SelectOption value="2">领取日期</SelectOption>
          </Select>
        )}
      </FormItem>
      {
        formVals.dateType == 1 ?
          <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="固定日期">
            {form.getFieldDecorator('validStartTime', {
              rules: [{ required: true, message: '请输入固定日期！' },],
              initialValue: formVals.validStartTime,
            })(<DatePicker format="YYYY-MM-DD" />)}
            &nbsp;-&nbsp;
            {form.getFieldDecorator('validEndTime', {
              rules: [{ required: true, message: '请输入固定日期！' },],
              initialValue: formVals.validEndTime,
            })(<DatePicker format="YYYY-MM-DD" />)}
          </FormItem> : ''
      }
      {
        formVals.dateType == 2 ?
          <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="领取日期">
            {form.getFieldDecorator('fixedBeginTerm', {
              rules: [{ required: true, message: '请输入固定日期！' },
                {min: 1, type: 'number', message: '最小值为 1'}],
              initialValue: formVals.fixedBeginTerm,
            })(<InputNumber placeholder="请输入" />)}
            &nbsp;-&nbsp;
            {form.getFieldDecorator('fixedEndTerm', {
              rules: [{ required: true, message: '请输入固定日期！' },
                {min: 1, type: 'number', message: '最小值为 1'}],
              initialValue: formVals.fixedEndTerm,
            })(<InputNumber placeholder="请输入" />)} 天
          </FormItem> : ''
      }

      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="优惠类型">
        {form.getFieldDecorator('preferentialType', {
          rules: [{ required: true, message: '请选择优惠类型！'}, // TODO 芋艿，需要修改
          ],
          initialValue: formVals.preferentialType,
        })(
          <Select placeholder="请选择" style={{ maxWidth: 200, width: '100%' }} onChange={onPreferentialTypeChange}>
            <SelectOption value="1">代金卷</SelectOption>
            <SelectOption value="2">折扣卷</SelectOption>
          </Select>
        )}
      </FormItem>
      {
        formVals.preferentialType == 1 ?
          <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="优惠金额">
            {form.getFieldDecorator('priceOff', {
              rules: [{ required: true, message: '请输入优惠金额！' },
                {min: 0.01, type: 'number', message: '最小值为 0.01'}],
              initialValue: formVals.priceOff,
            })(<InputNumber placeholder="请输入" />)}
          </FormItem> : ''
      }
      {
        formVals.preferentialType == 2 ?
          <span>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="折扣百分比">
              {form.getFieldDecorator('percentOff', {
                rules: [{ required: true, message: '请输入折扣百分比！' },
                  {min: 1, max: 99, type: 'number', message: '范围为 [1, 99]'},
                ],
                initialValue: formVals.percentOff,
              })(<InputNumber placeholder="请输入" />)}%
            </FormItem>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="最多优惠">
              {form.getFieldDecorator('discountPriceLimit', {
                rules: [{ required: false, message: '请输入最多优惠！' },
                  {min: 0.01, type: 'number', message: '最小值为 0.01'},
                ],
                initialValue: formVals.discountPriceLimit,
              })(<InputNumber placeholder="请输入" />)}元
            </FormItem>
          </span> : ''
      }
    </Modal>
  );
});

@connect(({ couponCardTemplateList }) => ({
  // list: productRecommend.list,
  // pagination: productRecommend.pagination,
  ...couponCardTemplateList,
}))

// 主界面
@Form.create()
class CouponCardTemplateLists extends PureComponent {

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'couponCardTemplateList/query',
      payload: {
        ...PaginationHelper.defaultPayload
      },
    });
  }

  handleModalVisible = (modalVisible, modalType, record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'couponCardTemplateList/setAll',
      payload: {
        modalVisible,
        modalType,
        formVals: record || {}
      },
    });
  };

  render() {
    // let that = this;
    const { dispatch,
      list, listLoading, searchParams, pagination,
      modalVisible, modalType, formVals,
      confirmLoading,  } = this.props;

    // 列表属性
    const listProps = {
      dataSource: list,
      pagination,
      searchParams,
      dispatch,
      loading: listLoading,
      confirmLoading,
      handleModalVisible: this.handleModalVisible, // Function
    };

    // 搜索表单属性
    const searchFormProps = {
      dispatch,
    };

    // 添加 or 更新表单属性
    const addOrUpdateFormProps = {
      modalVisible,
      modalType,
      formVals,
      dispatch,
      handleModalVisible: this.handleModalVisible, // Function
    };

    return (
      <PageHeaderWrapper>
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <SearchForm {...searchFormProps} />
            </div>
            <div className={styles.tableListOperator}>
              <Button
                icon="plus"
                type="primary"
                onClick={() => this.handleModalVisible(true, 'add', {})}
              >
                新建优惠劵
              </Button>
            </div>
          </div>
          <List {...listProps} />
        </Card>

        <AddOrUpdateForm {...addOrUpdateFormProps} />

      </PageHeaderWrapper>
    );
  }
}

export default CouponCardTemplateLists;
