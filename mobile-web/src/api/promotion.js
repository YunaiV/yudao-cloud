import request from "../config/request";

// Banner

export function getBannerList() {
  return request({
    url: 'promotion-api/users/banner/list',
    method: 'get',
  });
}

// Product Recommend

export function getProductRecommendList() {
  return request({
    url: 'promotion-api/users/product_recommend/list',
    method: 'get',
  });
}