import request from "../config/request";

export function getProductPage({cid, keyword, pageNo, pageSize, sortField, sortOrder}) {
  return request({
    url: '/search-api/users/product/page',
    method: 'get',
    params: {
      cid,
      keyword,
      pageNo: pageNo || 1,
      pageSize: pageSize || 10,
      sortField: sortField,
      sortOrder: sortOrder,
    }
  });
}
