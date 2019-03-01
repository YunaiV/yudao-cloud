import React, { Component } from 'react';
import { Button } from 'antd';
import AuthorityControl from '../../components/AuthorityControl';
import UrlsContext from '../../layouts/UrlsContext';

export default class Home extends Component {
  state = {};

  render() {
    // 定义认证的属性 TODO
    const GlobalAuthorityProps = {
      user: 'admin',
      login: 'success',
      authList: {
        'auth.button': true,
      },
    };

    return (
      <UrlsContext.Provider value={GlobalAuthorityProps}>
        <AuthorityControl authKey="home.button">
          <Button type="primary">按钮 控制</Button>
        </AuthorityControl>
        <h1>home...</h1>
      </UrlsContext.Provider>
    );
  }
}
