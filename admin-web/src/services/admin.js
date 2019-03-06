import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// auth

export async function getAdminMenus() {
  return request('/admin-api/admins/resource/admin_menu_tree');
}

export async function getAdminUrls(params) {
  return request(`/admin-api/admins/resource/admin_url_list?${stringify(params)}`);
}

// admin

export async function queryAdmin(params) {
  return request(`/admin-api/admins/admin/page?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function addAdmin(params) {
  return request(`/admin-api/admins/admin/add?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function updateAdmin(params) {
  return request(`/admin-api/admins/admin/update?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function updateAdminStatus(params) {
  return request(`/admin-api/admins/admin/update_status?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function deleteAdmin(params) {
  return request(`/admin-api/admins/admin/delete?${stringify(params)}`, {
    method: 'POST',
  });
}

// resource

export async function addResource(params) {
  return request(`/admin-api/admins/resource/add?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function updateResource(params) {
  return request(`/admin-api/admins/resource/update?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function deleteResource(params) {
  return request(`/admin-api/admins/resource/delete?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function resourceTree(params) {
  return request(`/admin-api/admins/resource/tree?${stringify(params)}`, {
    method: 'GET',
  });
}

// role

export async function queryRole(params) {
  return request(`/admin-api/admins/role/page?${stringify(params)}`);
}

export async function deleteRole(params) {
  return request(`/admin-api/admins/role/delete?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function addRole(params) {
  return request(`/admin-api/admins/role/add?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function updateRole(params) {
  return request(`/admin-api/admins/role/update?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}
