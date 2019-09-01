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

// product spu + sku

export async function productSpuPage(params) {
  return request(`/product-api/admins/spu/page?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function productSpuSearchList(params) {
  return request(`/product-api/admins/spu/search_list?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function productSpuList(params) {
  return request(`/product-api/admins/spu/list?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function productSpuAdd(params) {
  return request(`/product-api/admins/spu/add?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function productSpuUpdate(params) {
  return request(`/product-api/admins/spu/update?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function productSpuUpdateSort(params) {
  return request(`/product-api/admins/spu/update_sort?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function productSpuInfo(params) {
  return request(`/product-api/admins/spu/info?${stringify(params)}`, {
    method: 'GET',
  });
}

// product attr + attr value

export async function productAttrPage(params) {
  return request(`/product-api/admins/attr/page?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function productAttrAdd(params) {
  return request(`/product-api/admins/attr/add?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function productAttrUpdate(params) {
  return request(`/product-api/admins/attr/update?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function productAttrUpdateStatus(params) {
  return request(`/product-api/admins/attr/update_status?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function productAttrTree(params) {
  return request(`/product-api/admins/attr/tree?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function productAttrValueUpdate(params) {
  return request(`/product-api/admins/attr_value/update?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function productAttrValueUpdateStatus(params) {
  return request(`/product-api/admins/attr_value/update_status?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function productAttrValueAdd(params) {
  return request(`/product-api/admins/attr_value/add?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

// product brand  2019-05-31

export async function productBrandAdd(params) {
  return request(`/product-api/admins/brand/add?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function productBrandUpdate(params) {
  return request(`/product-api/admins/brand/update?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}

export async function productBrandGet(params) {
  return request(`/product-api/admins/brand/get?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function productBrandPage(params) {
  return request(`/product-api/admins/brand/page?${stringify(params)}`, {
    method: 'GET',
  });
}
