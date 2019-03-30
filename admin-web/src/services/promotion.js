import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// banner

export async function queryBanner(params) {
  return request(`/promotion-api/admins/banner/page?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function addBanner(params) {
  return request(`/promotion-api/admins/banner/add?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function updateBanner(params) {
  return request(`/promotion-api/admins/banner/update?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function updateBannerStatus(params) {
  return request(`/promotion-api/admins/banner/update_status?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function deleteBanner(params) {
  return request(`/promotion-api/admins/banner/delete?${stringify(params)}`, {
    method: 'POST',
  });
}