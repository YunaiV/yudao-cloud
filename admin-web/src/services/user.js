import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

export async function query() {
  return request('/api/users');
}

export async function queryCurrent() {
  return request('/api/currentUser');
}

// User

export async function queryUserPage(params) {
  return request(`/user-api/admins/user/page?${stringify(params)}`, {
    method: 'GET',
  });
}
