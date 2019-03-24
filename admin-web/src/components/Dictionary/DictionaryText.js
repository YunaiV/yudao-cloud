import React, { PureComponent } from 'react';
import DictionaryContext from './DictionaryContext';

export default class DictionaryText extends PureComponent {
  componentDidMount() {}

  render() {
    // debugger;
    const { dicKey, dicValue } = this.props;
    return (
      <DictionaryContext.Consumer>
        {context => {
          const dicValues = context[dicKey];
          if (dicValues) {
            return dicValues[dicValue];
          }
          return dicValue;
        }}
      </DictionaryContext.Consumer>
    );
  }
}
