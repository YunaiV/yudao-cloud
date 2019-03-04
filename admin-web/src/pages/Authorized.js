import React from 'react';
import RenderAuthorized from '@/components/Authorized';
import { getAuthority } from '@/utils/authority';
import Redirect from 'umi/redirect';

const Authority = getAuthority();

// TODO RenderAuthorized 暂时写死为 admin，次组件集成于 antd-pro 后期有时间处理，（可能有用，可能没用）
// TODO 可大致分为两种角色，admin 管理员角色，user 代表其他非授权页面，可以公开的
// const Authorized = RenderAuthorized(['admin', 'user']);
const Authorized = RenderAuthorized(Authority);

export default ({ children }) => {
  return (
    <Authorized authority={children.props.route.authority} noMatch={<Redirect to="/user/login" />}>
      {children}
    </Authorized>
  );
};
