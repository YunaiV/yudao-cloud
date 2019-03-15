import * as React from 'react';
import { Select } from 'antd';

export interface IDictionarySelectProps extends Select {
  dicKey?: string;
  defaultValue?: string | number | boolean;
}

export default class DictionarySelectD extends React.Component<IDictionarySelectProps, any> {}
