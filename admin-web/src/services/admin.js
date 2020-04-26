import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// auth

export async function getAdminUrls(params) {
  return request(`/admin-api/admins/admin/url_resource_list?${stringify(params)}`);
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

export async function queryAdminRoleList(params) {
  return request(`/admin-api/admins/admin/role_list?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function adminRoleAssign(params) {
  return request(`/admin-api/admins/admin/assign_role?${stringify(params)}`, {
    method: 'POST',
  });
}

// deptment
export async function addDeptment(params) {
  return request('/admin-api/admins/dept/add', {
    method: 'POST',
    body: {
      ...params,
    },
  });
}

export async function updateDeptment(params) {
  return request('/admin-api/admins/dept/update', {
    method: 'POST',
    body: {
      ...params,
    },
  });
}

export async function deleteDeptment(params) {
  return request(`/admin-api/admins/dept/delete?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function deptTreePage(params) {
  return request(`/admin-api/admins/dept/tree/page?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function deptTreeAll() {
  return request('/admin-api/admins/dept/tree/all', {
    method: 'GET',
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

export async function queryRoleResourceTree(params) {
  return request(`/admin-api/admins/role/resource_tree?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function roleAssignResource(params) {
  return request(`/admin-api/admins/role/assign_resource?${stringify(params)}`, {
    method: 'POST',
    body: {
      ...params,
    },
  });
}

// dictionary

export async function dictionaryTree(params) {
  return request(`/admin-api/admins/data_dict/tree?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function dictionaryList(params) {
  return request(`/admin-api/admins/data_dict/list?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function dictionaryAdd(params) {
  return request(`/admin-api/admins/data_dict/add?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function dictionaryUpdate(params) {
  return request(`/admin-api/admins/data_dict/update?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function dictionaryDelete(params) {
  return request(`/admin-api/admins/data_dict/delete?${stringify(params)}`, {
    method: 'POST',
  });
}

// file

export async function fileGetQiniuToken() {
  return request(`/admin-api/admins/file/get-qiniu-token`, {
    method: 'GET',
  });
}

// export async function fileUploadQiniu(fileData) {
//   return request(`/qiniu/upload`, {
//     method: 'POST',
//   });
// }
