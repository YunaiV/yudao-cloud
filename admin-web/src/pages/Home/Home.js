import React, { Component } from 'react';
import { Button } from 'antd';
import AuthorityControl from '../../components/AuthorityControl';
import GlobalAuthority from '../../layouts/GlobalAuthorityContext';

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
      <GlobalAuthority.Provider value={GlobalAuthorityProps}>
        <AuthorityControl authKey="home.button">
          <Button type="primary">按钮 控制</Button>
        </AuthorityControl>
        <h1>home...</h1>
      </GlobalAuthority.Provider>
    );
  }
}
