import request from "../config/request";

// Banner

export function getBannerList() {
  return request({
    url: 'promotion-api/users/banner/list',
    method: 'get',
  });
}