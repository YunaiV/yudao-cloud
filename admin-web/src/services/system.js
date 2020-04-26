import {stringify} from '@/utils/request.qs';
import request from '@/utils/request';

// ========== OAuth2 模块 ==========

export async function oauth2UsernameAuthenticate(params) {
  return request(`/system-api/admins/oauth2/username-authenticate?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

// ========== Authorization 模块 ==========

export async function authorizationMenuResourceTree() {
  return request('/system-api/admins/authorization/menu-resource-tree', {
    method: 'GET',
  });
}

export async function authorizationResourcePermissions(params) {
  return request(`/system-api/admins/authorization/resource-permissions`, {
    method: 'GET',
  });
}
