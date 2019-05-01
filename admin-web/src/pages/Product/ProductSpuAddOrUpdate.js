/* eslint-disable */

import React, {PureComponent, Fragment, Component} from 'react';
import { connect } from 'dva';
import moment from 'moment';
import {Card, Form, Input, Radio, Button, Modal, Select, Upload, Icon} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

import * as qiniu from 'qiniu-js'

import styles from './ProductSpuAddOrUpdate.less';
import ProductAttrSelectFormItem from "../../components/Product/ProductAttrSelectFormItem";
import ProductSkuAddOrUpdateTable from "../../components/Product/ProductSkuAddOrUpdateTable";
import {fileGetQiniuToken, fileUploadQiniu} from "../../services/admin";

const FormItem = Form.Item;
const RadioGroup = Radio.Group;
const Option = Select.Option;

class PicturesWall extends React.Component {
  state = {
    token: undefined, // 七牛 token


    previewVisible: false,
    previewImage: '',

    fileList: [{ // 目前图片
      uid: -1,
      name: 'xxx.png',
      status: 'done',
      url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
    }],
  };

  handleCancel = () => this.setState({ previewVisible: false })

  handlePreview = (file) => {
    this.setState({
      previewImage: file.url || file.thumbUrl,
      previewVisible: true,
    });
  }

  beforeUpload = async () => {
    const tokenResult = await fileGetQiniuToken();
    if (tokenResult.code !== 0) {
      alert('获得七牛上传 Token 失败');
      return false;
    }
    this.setState({
      token: tokenResult.data,
    });
    return true;
  };

  customRequest = ({action,
                     file,
                     headers,
                     onError,
                     onProgress,
                     onSuccess,
                     withCredentials,}) => {
    // 使用 FileReader 将上传的文件转换成二进制流，满足 'application/octet-stream' 格式的要求
    const reader = new FileReader();
    reader.readAsArrayBuffer(file);
    let fileData = null;
    reader.onload = (e) => {
      // 在文件读取结束后执行的操作
      fileData = e.target.result;
      // 上传文件
      // fileUploadQiniu(fileData);
      // debugger;
      // 使用 axios 进行文件上传的请求
      // axios.put(action, fileData, {
      //   withCredentials,
      //   headers,
      //   onUploadProgress: ({ total, loaded }) => {
      //     // 进行上传进度输出，更加直观
      //     onProgress({ percent: Math.round(loaded / total * 100).toFixed(2) }, file);
      //   },
      // }).then(response => {
      //   onSuccess(response, file);
      // })
      //   .catch(onError);
      let observable = qiniu.upload(file, '123', this.state.token);
      observable.subscribe(function () {
        // next
      }, function () {
        // error
      }, function () {
        // complete
      });
    };
    return {
      abort() {
        console.log('upload progress is aborted.');
      },
    };
  };

  handleChange = ({ fileList }) => this.setState({ fileList })

  render() {
    const { previewVisible, previewImage, fileList } = this.state;
    const uploadButton = (
      <div>
        <Icon type="plus" />
        <div className="ant-upload-text">Upload</div>
      </div>
    );
    return (
      <div className="clearfix">
        <Upload
          // action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
          action="https://up-z2.qiniu.com"
          listType="picture-card"
          fileList={fileList}
          onPreview={this.handlePreview}
          onChange={this.handleChange}
          beforeUpload={this.beforeUpload}
          customRequest={this.customRequest}
        >
          {fileList.length >= 3 ? null : uploadButton}
        </Upload>
        <Modal visible={previewVisible} footer={null} onCancel={this.handleCancel}>
          <img alt="example" style={{ width: '100%' }} src={previewImage} />
        </Modal>
      </div>
    );
  }
};

// roleList
@connect(({ productSpuList, productAttrList, productSpuAddOrUpdate, loading }) => ({
  // list: productSpuList.list.spus,
  // loading: loading.models.productSpuList,
  productAttrList,
  productSpuAddOrUpdate,
  allAttrTree: productAttrList.tree,
  attrTree: productSpuAddOrUpdate.attrTree,
  skus: productSpuAddOrUpdate.skus,
}))

@Form.create()
class ProductSpuAddOrUpdate extends Component {
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
      type: 'productAttrList/tree',
      payload: {
        name: '',
        pageNo: 0,
        pageSize: 10,
      },
    });
  }

  handleAddAttr = e => {
    // alert('你猜');
    const { dispatch } = this.props;
    dispatch({
      type: 'productSpuAddOrUpdate/addAttr',
      payload: {
      },
    });
  }

  handleSubmit = e => {
    debugger;
    e.preventDefault();
    const { skus, dispatch } = this.props;
    // 生成 skuStr 格式
    let skuStr = []; // 因为提交是字符串格式
    for (let i in skus) {
      let sku = skus[i];
      if (!sku.price || !sku.quantity) {
        continue;
      }
      let newAttr = {
        attrs: [],
        price: sku.price * 100,
        quantity: sku.quantity,
      }
      for (let j in sku.attrs) {
        newAttr.attrs.push(sku.attrs[j].id);
      }
      skuStr.push(newAttr);
    }
    if (skuStr.length === 0) {
      alert('请设置商品规格！');
      return;
    }
    this.props.form.validateFields((err, values) => {
      if (!err) {
        dispatch({
          type: 'productSpuAddOrUpdate/add',
          payload: {
            body: {
              ...values,
              skuStr: JSON.stringify(skuStr)
            }
          },
        });
      }
    });
    // console.log(fields);
  }

    // handleSelectAttr = (value, option) => {
  //   // console.log(value);
  //   // console.log(option);
  //   // debugger;
  //   const { dispatch } = this.props;
  //   let attrIndex = option.key.substring(option.key.indexOf('option-attr-') + 'option-attr-'.length, option.key.lastIndexOf('-'));
  //   // console.log('attrIndex: ' + attrIndex);
  //   // debugger;
  //   dispatch({
  //     type: 'productSpuAddOrUpdate/selectAttr',
  //     payload: {
  //       attrIndex: attrIndex,
  //       attr: {
  //         id: option.props.value,
  //         name: option.props.children,
  //         values: []
  //       }
  //     },
  //   });
  // }
  //
  // handleSelectAttrValue = (values, options) => {
  //   let attrValues = [];
  //   const { dispatch } = this.props;
  //   debugger;
  //   // console.log('x' + this.children[0]);
  //   let firstOption = this.children[0];
  //   // let attrIndex = firstOption.key.substring(firstOption.key.indexOf('option-attr-value-') + 'option-attr-value-'.length, firstOption.key.lastIndexOf('-'));
  //   let attrIndex = 0;
  //   for (let i in options) {
  //     let option = options[i];
  //     attrValues.push({
  //       id: parseInt(option.props.value),
  //       name: option.props.children,
  //     });
  //   }
  //   dispatch({
  //     type: 'productSpuAddOrUpdate/selectAttrValues',
  //     payload: {
  //       attrIndex: attrIndex,
  //       attrValues: attrValues,
  //     },
  //   });
  //   // debugger;
  //
  //   // console.log(value);
  // }

  render() {
    // debugger;
    const { form, skus, attrTree, allAttrTree, dispatch } = this.props;
    // const that = this;

    // 添加规格
    // debugger;
    let attrTreeHTML = [];
    if (attrTree && attrTree.length > 0) {
      // 已选择的的规格集合
      let selectedAttrIds = new Set();
      for (let i in attrTree) {
        let attr = attrTree[i];
        selectedAttrIds.add(attr.id);
      }
      // 创建每个规格下拉框的 HTML
      for (let i in attrTree) {
        let attr = attrTree[i];
        let itemProps = {
          attr: attr,
          allAttrTree: allAttrTree,
          dispatch: dispatch,
          selectedAttrIds: selectedAttrIds,
          index: i // 位置。不然无法正确修改 Model 指定位置的数据
        };
        attrTreeHTML.push(<ProductAttrSelectFormItem {...itemProps}  />);
      }
    }
    // if (attrTree && attrTree.length > 0) {
    //   for (let i in attrTree) {
    //     let attr = attrTree[i];
    //     // console.log('i: ' + i);
    //     // 1. 规格
    //     let attrOptions = [];
    //     for (let j in allAttrTree) {
    //       let attr = allAttrTree[j];
    //       attrOptions.push(<Option key={`option-attr-${i}-${attr.id}`} value={attr.id}>{attr.name}</Option>);
    //     }
    //     // 2. 规格值
    //     let attrValueOptions = [];
    //     // debugger;
    //     if (attr.id)   {
    //       // 2.1 先招到规格值的数组
    //       let attrValues = [];
    //       for (let j in allAttrTree) {
    //         let allAttr = allAttrTree[j];
    //         if (attr.id === allAttr.id) {
    //           attrValues = allAttr.values;
    //           break;
    //         }
    //       }
    //       // 2.2 生成规格值的 HTML
    //       for (let j in attrValues) {
    //         let attrValue = attrValues[j];
    //         attrValueOptions.push(<Option key={`option-attr-value-${i}-${attrValue.id}`} value={attrValue.id + ''}>{attrValue.name}</Option>); // + '' 的原因是，多选必须是字符串
    //       }
    //     }
    //     // 3. 拼装最终，添加到 attrTreeHTML 中
    //     attr = <div key={`div-attr-${i}`}>
    //       <Select key={`select-attr-${i}`} style={{ width: 120 }} placeholder='请选择规格' onChange={that.handleSelectAttr}>
    //         {attrOptions}
    //       </Select>
    //       <Select key={`select-attr-value-${i}`} mode={"tags"} style={{ width: 260 }} placeholder='请选择规格值' onChange={that.handleSelectAttrValue}>
    //         {attrValueOptions}
    //       </Select>
    //     </div>;
    //     attrTreeHTML.push(attr);
    //   }
    // }
    // 规格明细
    let productSkuProps = {
      attrTree: attrTree,
      skus: skus,
      dispatch: dispatch,
    };
    // console.log(productSkuProps);

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
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="分类编号">
              {form.getFieldDecorator('cid', {
                rules: [{ required: true, message: '请输入分类编号！' }],
                initialValue: '', // TODO 修改 // TODO 芋艿，和面做成下拉框
              })(<Input placeholder="请输入" />)}
            </FormItem>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="商品主图">
              {/*{form.getFieldDecorator('picUrls', {*/}
              {/*  initialValue: '', // TODO 修改 // TODO 芋艿，做成上传组件*/}
              {/*})(<Input placeholder="请输入" />)}*/}
              <PicturesWall />
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
                <div>
                  {attrTreeHTML}
                  <Button onClick={this.handleAddAttr}>添加规格项目</Button>
                </div>
              )}
            </FormItem>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="规格明细">
              {/*<Table defaultExpandAllRows={true} columns={columns} rowKey="id" />*/}
              <ProductSkuAddOrUpdateTable {...productSkuProps} />
            </FormItem>
            <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="商品描述">
              {form.getFieldDecorator('description', {
                rules: [{ required: true, message: '请输入商品描述！' }],
                initialValue: '', // TODO 修改
              })(<Input.TextArea placeholder="请输入" />)}
              <Button type="primary" htmlType="submit" style={{ marginLeft: 8 }} onSubmit={this.handleSubmit}>保存</Button>
            </FormItem>
          </Form>
        </Card>
      </PageHeaderWrapper>
    );
  }
}

export default ProductSpuAddOrUpdate;
