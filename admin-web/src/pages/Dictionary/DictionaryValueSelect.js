import React, { PureComponent } from 'react';
import { connect } from 'dva';
import DictionarySelect from '../../components/Dictionary/DictionarySelect';

@connect(({ dictionarySelect, loading }) => ({
  data: dictionarySelect,
  loading: loading.models.dictionarySelect,
}))
class DictionaryValueSelect extends PureComponent {
  componentDidMount() {
    const { dataKey, dispatch } = this.props;
    dispatch({
      type: 'dictionarySelect/query',
      payload: {
        dataKey,
      },
    });
  }

  render() {
    const { data } = this.props;
    return <DictionarySelect {...this.props} list={data.list} />;
  }
}

export default DictionaryValueSelect;
