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

// product recommend

export async function queryProductRecommend(params) {
  return request(`/promotion-api/admins/product_recommend/page?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function addProductRecommend(params) {
  return request(`/promotion-api/admins/product_recommend/add?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function updateProductRecommend(params) {
  return request(`/promotion-api/admins/product_recommend/update?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function updateProductRecommendStatus(params) {
  return request(`/promotion-api/admins/product_recommend/update_status?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function deleteProductRecommend(params) {
  return request(`/promotion-api/admins/product_recommend/delete?${stringify(params)}`, {
    method: 'POST',
  });
}

// coupon

export async function addCouponCardTemplate(params) {
  return request(`/promotion-api/admins/coupon/template/add_card?${stringify(params)}`, {
    method: 'POST',
  });
}
