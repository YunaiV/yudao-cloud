import { stringify } from 'qs';
import request from '@/utils/request';

// auth

export async function getAdminMenus() {
  return request('/admin-api/admin/resource/admin_menu_tree');
}

export async function getAdminUrls(params) {
  return request(`/admin-api/admin/resource/admin_url_list?${stringify(params)}`);
}

// resource

export async function addResource(params) {
  return request(`/admin-api/admin/resource/add?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function updateResource(params) {
  return request(`/admin-api/admin/resource/update?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function deleteResource(params) {
  return request(`/admin-api/admin/resource/delete?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function resourceTree(params) {
  return request(`/admin-api/admin/resource/tree?${stringify(params)}`, {
    method: 'GET',
  });
}

// role

export async function queryRole(params) {
  return request(`/admin-api/admin/role/page?${stringify(params)}`);
}

export async function deleteRole(params) {
  return request(`/admin-api/admin/role/delete?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function addRole(params) {
  return request(`/admin-api/admin/role/add?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function updateRole(params) {
  return request(`/admin-api/admin/role/update?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}
