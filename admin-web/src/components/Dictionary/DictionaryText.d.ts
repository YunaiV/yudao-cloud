import { Select } from 'antd';
import * as React from 'react';

export interface IDictionaryTextProps extends Select {
  dicKey?: string;
  dicValue?: string | number | boolean | Array<string | number | boolean>;
}

// eslint-disable-next-line react/prefer-stateless-function
export default class DictionaryText extends React.Component<IDictionaryTextProps, any> {}
