import * as React from 'react';
import { Select } from 'antd';

export interface IDictionaryTextProps extends Select {
  dicKey?: string;
  dicValue?: string | number | boolean | Array<string | number | boolean>;
}

export default class DictionaryText extends React.Component<IDictionaryTextProps, any> {}
