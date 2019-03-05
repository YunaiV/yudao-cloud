import { stringify } from 'qs';
import request from '@/utils/request';

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
