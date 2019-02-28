import React from 'react';

// 创建全局的权限控制 context，方便在所有页面使用
const GlobalAuthorityContext = React.createContext({
  theme: 'dark',
  toggle: () => {},
});

export default GlobalAuthorityContext;
