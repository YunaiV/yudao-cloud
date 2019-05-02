import React, {PureComponent} from "react";
import {InputNumber, Select, Table} from "antd";
import Input from "antd/es/input";

const Option = Select.Option;

class SkuInputNumber extends PureComponent {

  handleChange = value => {
    // debugger;
    const { dispatch, index, dataIndex } = this.props;
    if (dataIndex === 'price') {
      dispatch({
        type: 'productSpuAddOrUpdate/inputSkuPrice',
        payload: {
          index: index,
          price: value
        },
      });
    } else if (dataIndex === 'quantity') {
      dispatch({
        type: 'productSpuAddOrUpdate/inputSkuQuantity',
        payload: {
          index: index,
          quantity: value
        },
      });
    }
  }

  render() {
    const { value } = this.props;
    return <InputNumber placeholder="请输入" value={value} onChange={this.handleChange} />
  }

}

export default class ProductSkuAddOrUpdateTable extends PureComponent {

  render() {
    let that = this;
    // debugger;
    // console.log('ProductSkuAddOrUpdateTable');
    const {attrTree, skus, dispatch} = this.props;
    let columns = [];
    for (let i in attrTree) {
      let attr = attrTree[i];
      columns.push({
        title: attr.name,
        dataIndex: 'attrs[i]',
        render(value, record) {
          return record.attrs[i].name;
        }
      })
    }
    columns.push({
      title: '价格',
      dataIndex: 'price',
      render(value, record, index) {
        let props = {
          record: record,
          index: index,
          dispatch: dispatch,
          dataIndex: 'price',
          value: record.price,
        };
        return <SkuInputNumber {...props} />;
      }
    });
    columns.push({
      title: '库存',
      dataIndex: 'quantity',
      render(value, record, index) {
        let props = {
          record: record,
          index: index,
          dispatch: dispatch,
          dataIndex: 'quantity',
          value: record.quantity,
        };
        return <SkuInputNumber {...props} />;
      }
    });
    return <Table columns={columns} dataSource={skus} rowKey="index" pagination={false} />;
    // return <div />;
  }

}
