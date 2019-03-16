import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// product category

export async function productCategoryTree(params) {
    return request(`/product-api/admins/category/tree?${stringify(params)}`, {
        method: 'GET',
    });
}

export async function productCategoryAdd(params) {
    return request(`/product-api/admins/category/add?${stringify(params)}`, {
        method: 'POST',
        body: {},
    });
}

export async function productCategoryUpdate(params) {
    return request(`/product-api/admins/category/update?${stringify(params)}`, {
        method: 'POST',
        body: {},
    });
}

export async function productCategoryUpdateStatus(params) {
  return request(`/product-api/admins/category/update_status?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function productCategoryDelete(params) {
    return request(`/product-api/admins/category/delete?${stringify(params)}`, {
        method: 'POST',
    });
}

// product spu

export async function productSpuPage(params) {
  return request(`/product-api/admins/spu/page?${stringify(params)}`, {
    method: 'GET',
  });
}