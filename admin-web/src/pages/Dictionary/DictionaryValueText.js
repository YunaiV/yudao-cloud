import React, { PureComponent } from 'react';
import { connect } from 'dva';

@connect(({ dictionarySelect, loading }) => ({
  data: dictionarySelect,
  loading: loading.models.dictionarySelect,
}))
class DictionaryValueText extends PureComponent {
  componentDidMount() {
    const { dataKey, dispatch } = this.props;
    dispatch({
      type: 'dictionarySelect/queryText',
      payload: {
        dataKey,
        value: 1,
      },
    });
  }

  render() {
    const { data } = this.props;
    return <span>{data.text}</span>;
  }
}

export default DictionaryValueText;
