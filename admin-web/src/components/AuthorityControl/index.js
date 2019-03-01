import React, { PureComponent } from 'react';
import UrlsContext from '../../layouts/UrlsContext';

// 用于控制权限
class AuthorityControl extends PureComponent {
  render() {
    const { authKey, children } = this.props;
    return (
      <UrlsContext.Consumer>
        {context => {
          const { authList } = context;
          return <div>{authList[authKey] ? children : '无权限'}</div>;
        }}
      </UrlsContext.Consumer>
    );
  }
}

export default AuthorityControl;
