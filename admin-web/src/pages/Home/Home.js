import React, { Component } from 'react';
import { Button } from 'antd';
import DictionarySelect from '@/components/Dictionary/DictionarySelect';
import DictionaryText from '@/components/Dictionary/DictionaryText';
import AuthorityControl from '@/components/AuthorityControl';

export default class Home extends Component {
  state = {};

  render() {
    return (
      <div>
        <AuthorityControl authKey="home.button">
          <Button type="primary">按钮 控制</Button>
        </AuthorityControl>
        <h1>home...</h1>

        <DictionarySelect dicKey="gender" defaultValue="1" />
        <DictionaryText dicKey="gender" dicValue="2" />
      </div>
    );
  }
}
