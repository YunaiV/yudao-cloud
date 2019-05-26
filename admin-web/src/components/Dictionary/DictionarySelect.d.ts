import { Select } from 'antd';
import * as React from 'react';

export interface IDictionarySelectProps extends Select {
  dicKey?: string;
  defaultValue?: string | number | boolean;
}

// eslint-disable-next-line react/prefer-stateless-function
export default class DictionarySelectD extends React.Component<IDictionarySelectProps, any> {}
