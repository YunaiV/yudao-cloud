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

export async function authorizationRoleResourceTree(params) {
  return request(`/system-api/admins/authorization/role_resource_tree?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function authorizationRoleAssignResource(params) {
  return request(`/system-api/admins/authorization/assign_role_resource?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

// ========== Resource 模块 ==========

export async function resourceTree(params) {
  return request(`/system-api/admins/resource/tree`, {
    method: 'GET',
  });
}

export async function resourceAdd(params) {
  return request(`/system-api/admins/resource/add?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function resourceUpdate(params) {
  return request(`/system-api/admins/resource/update?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function resourceDelete(params) {
  return request(`/system-api/admins/resource/delete?${stringify(params)}`, {
    method: 'POST',
  });
}

// ========== Role 模块 ==========

export async function rolePage(params) {
  return request(`/system-api/admins/role/page?${stringify(params)}`);
}

export async function roleDelete(params) {
  return request(`/system-api/admins/role/delete?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function roleAdd(params) {
  return request(`/system-api/admins/role/add?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function roleUpdate(params) {
  return request(`/system-api/admins/role/update?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

// ========== Admin 模块 ==========

export async function adminPage(params) {
  return request(`/system-api/admins/admin/page?${stringify(params)}`, {
    method: 'GET',
  });
}
