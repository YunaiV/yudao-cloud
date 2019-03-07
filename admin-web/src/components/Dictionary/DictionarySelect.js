import React, { PureComponent } from 'react';
import { Select } from 'antd';

export default class DictionarySelect extends PureComponent {
  renderOptions() {
    const { list } = this.props;
    return list.map(item => {
      return (
        <Select.Option key={item.value} value={item.value}>
          {item.text}
        </Select.Option>
      );
    });
  }

  render() {
    const options = this.renderOptions();
    return <Select {...this.props}>{options}</Select>;
  }
}
