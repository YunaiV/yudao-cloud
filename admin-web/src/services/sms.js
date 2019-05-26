import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// sign

export async function pageSign(params) {
  return request(`/admin-api/admins/sms/sign/page?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function addSign(params) {
  return request(`/admin-api/admins/sms/sign/add?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function updateSign(params) {
  return request(`/admin-api/admins/sms/sign/update?${stringify(params)}`, {
    method: 'PUT',
  });
}

export async function deletedSign(params) {
  return request(`/admin-api/admins/sms/sign/deleted?${stringify(params)}`, {
    method: 'DELETE',
  });
}
