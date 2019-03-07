import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

export async function queryKey(params) {
  return request(`/admin-api/admins/dictionary/getList?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function queryText(params) {
  return request(`/admin-api/admins/dictionary/queryText?${stringify(params)}`, {
    method: 'GET',
  });
}
