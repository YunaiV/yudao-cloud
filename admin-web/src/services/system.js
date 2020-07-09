import {stringify} from '@/utils/request.qs';
import request from '@/utils/request';

// ========== Passport 模块 ==========

export async function passportLogin(params) {
  return request(`/management-api/passport/login?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

// ========== Authorization 模块 ==========

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
  return request(`/management-api/resource/tree`, {
    method: 'GET',
  });
}

export async function resourceCreate(params) {
  return request(`/management-api/resource/create?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function resourceUpdate(params) {
  return request(`/management-api/resource/update?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function resourceDelete(params) {
  return request(`/management-api/resource/delete?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function resourceTreeAdminMenu() {
  return request('/management-api/resource/tree-admin-menu', {
    method: 'GET',
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
