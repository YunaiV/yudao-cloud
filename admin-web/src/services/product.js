import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// dictionary

export async function productCategoryTree(params) {
    return request(`/product-api/admins/category/tree?${stringify(params)}`, {
        method: 'GET',
    });
}

export async function dictionaryAdd(params) {
    return request(`/admin-api/admins/data_dict/add?${stringify(params)}`, {
        method: 'POST',
        body: {},
    });
}

export async function dictionaryUpdate(params) {
    return request(`/admin-api/admins/data_dict/update?${stringify(params)}`, {
        method: 'POST',
        body: {},
    });
}

export async function dictionaryDelete(params) {
    return request(`/admin-api/admins/data_dict/delete?${stringify(params)}`, {
        method: 'POST',
    });
}