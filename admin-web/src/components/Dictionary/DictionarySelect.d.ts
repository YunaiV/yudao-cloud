import * as React from 'react';
import { Select } from 'antd';

export interface DictionaryObject {
  text?: string;
  value?: string | number | boolean;
}

export interface IDictionarySelectProps extends Select {
  list?: DictionaryObject[];
}

export default class DictionarySelectD extends React.Component<IDictionarySelectProps, any> {}
