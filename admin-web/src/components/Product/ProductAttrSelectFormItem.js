import React, {PureComponent} from "react";
import {Select} from "antd";

const Option = Select.Option;

class AttrValueSelect extends Select {

  handleSelectAttrValue = (value, option) => {

  };

  render() {
    const {index, attrValues, attrValueOptions, attr} = this.props;

    const handleChangeAttrValue = async (values, options) => {
      let attrValues = [];
      const {dispatch, index} = this.props;
      // debugger;
      // console.log('x' + this.children[0]);
      // let firstOption = this.children[0];
      // let attrIndex = firstOption.key.substring(firstOption.key.indexOf('option-attr-value-') + 'option-attr-value-'.length, firstOption.key.lastIndexOf('-'));
      for (let i in options) {
        let option = options[i];
        // TODO 芋艿，这个做法很不优雅，后面看俺。需要问下 answer 。
        let attrValueId = undefined;
        if (option.key.indexOf('option-attr-value-') === -1) {
          await dispatch({
            type: 'productAttrList/addValue',
            payload: {
              attrId: attr.id,
              name: option.props.children,
            },
            callback: function (data) {
              attrValueId = data.id;
            }
          });
        } else {
          attrValueId = parseInt(option.props.value);
        }
        // 添加到 attrValues 中。
        // debugger;
        attrValues.push({
          id: attrValueId,
          name: option.props.children,
        });
      }
      dispatch({
        type: 'productSpuAddOrUpdate/selectAttrValues',
        payload: {
          attrIndex: index,
          attrValues: attrValues,
        },
      });
      // debugger;

      // console.log(value);
    };

    return <Select key={`select-attr-value-${index}`} mode={"tags"} style={{width: 260}} value={attrValues}
                   placeholder='请选择规格值'
                   onChange={handleChangeAttrValue} onSelect={this.handleSelectAttrValue}>
      {attrValueOptions}
    </Select>;
  }

}

export default class ProductAttrSelectFormItem extends PureComponent {

  handleSelectAttr = (value, option) => {
    debugger;
    // console.log(value);
    // console.log(option);
    // debugger;
    const { dispatch, index } = this.props;
    // let attrIndex = option.key.substring(option.key.indexOf('option-attr-') + 'option-attr-'.length, option.key.lastIndexOf('-'));
    // console.log('attrIndex: ' + attrIndex);
    // debugger;
    dispatch({
      type: 'productSpuAddOrUpdate/selectAttr',
      payload: {
        attrIndex: index,
        attr: {
          id: option.props.value,
          name: option.props.children,
          values: []
        }
      },
    });
  };

  render() {
    const {attr, allAttrTree, selectedAttrIds, index, dispatch} = this.props;
    // console.log('i: ' + i);
    // 1. 规格
    let attrOptions = [];
    // allAttrTree.unshift(attr);
    // debugger;
    for (let j in allAttrTree) {
      let allAttr = allAttrTree[j];
      if (selectedAttrIds.has(allAttr.id) && allAttr.id !== attr.id) {
        continue;
      }
      attrOptions.push(<Option key={`option-attr-${index}-${allAttr.id}`} value={allAttr.id}>{allAttr.name}</Option>);
    }
    // 2. 规格值
    let attrValueOptions = [];
    // debugger;
    if (attr.id) {
      // 2.1 先找到规格值的数组
      let attrValues = [];
      for (let j in allAttrTree) {
        let allAttr = allAttrTree[j];
        if (attr.id === allAttr.id) {
          attrValues = allAttr.values;
          break;
        }
      }
      // 2.2 生成规格值的 HTML
      for (let j in attrValues) {
        let attrValue = attrValues[j];
        attrValueOptions.push(<Option key={`option-attr-value-${index}-${attrValue.id}`}
                                      value={attrValue.id + ''}>{attrValue.name}</Option>); // + '' 的原因是，多选必须是字符串
      }
    }
    // 3. 拼装最终，添加到 attrTreeHTML 中
    // debugger;
    let attrValues = []; // 选中的规格值集合
    for (let i in attr.values) {
      attrValues.push(attr.values[i].id + ''); // Select 传入数组时，如果不 + '' ，选不中。
    }
    let attrValueSelectProps = {
      index: index,
      attrValues: attrValues,
      dispatch: dispatch,
      attrValueOptions: attrValueOptions,
      attr: attr,
    };
    // TODO BUG ，规格不能搜索添加
    return <div key={`div-attr-${index}`}>
      <Select key={`select-attr-${index}`} showSearch optionFilterProp="children" style={{width: 120}} placeholder='请选择规格' value={attr.id} onChange={this.handleSelectAttr} onSearch={this.handleSearchAttr}>
        {attrOptions}
      </Select>
      <AttrValueSelect {...attrValueSelectProps} />
    </div>;
  }

}
