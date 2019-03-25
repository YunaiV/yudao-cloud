import request from "../config/request";

export function getProductCategoryList(pid) {
  return request({
    url: 'product-api/users/category/list',
    method: 'get',
    params: {
      pid
    }
  });
}