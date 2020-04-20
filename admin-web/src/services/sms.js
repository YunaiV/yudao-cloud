import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// sign

export async function pageSign(params) {
  return request(`/admin-api/admins/sms/sign/page?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function addSign(params) {
  return request(`/admin-api/admins/sms/sign/add`, {
    method: 'POST',
    body: {
      ...params,
    },
  });
}

export async function updateSign(params) {
  return request(`/admin-api/admins/sms/sign/update`, {
    method: 'PUT',
    body: {
      ...params,
    },
  });
}

export async function deletedSign(params) {
  return request(`/admin-api/admins/sms/sign/deleted?${stringify(params)}`, {
    method: 'DELETE',
  });
}

// template

export async function pageTemplate(params) {
  return request(`/admin-api/admins/sms/template/page`, {
    method: 'POST',
    body: {
      ...params,
    },
  });
}

export async function addTemplate(params) {
  return request(`/admin-api/admins/sms/template/add?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function updateTemplate(params) {
  return request(`/admin-api/admins/sms/template/update?${stringify(params)}`, {
    method: 'PUT',
  });
}

export async function deletedTemplate(params) {
  return request(`/admin-api/admins/sms/template/deleted?${stringify(params)}`, {
    method: 'DELETE',
  });
}
