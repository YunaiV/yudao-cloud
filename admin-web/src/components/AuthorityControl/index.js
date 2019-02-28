import React, { PureComponent } from 'react';
import GlobalAuthority from '../../layouts/GlobalAuthority';

// 用于控制权限
class AuthorityControl extends PureComponent {
  render() {
    const { authKey, children } = this.props;
    return (
      <GlobalAuthority.Consumer>
        {context => {
          const { authList } = context;
          return <div>{authList[authKey] ? children : '无权限'}</div>;
        }}
      </GlobalAuthority.Consumer>
    );
  }
}

export default AuthorityControl;
