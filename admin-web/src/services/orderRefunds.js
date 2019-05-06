import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// order
export async function list(params) {
  return request(`/order-api/admins/order_return/list?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function orderPage(params) {
  return request(`/order-api/admins/order/page?${stringify(params)}`, {
    method: 'GET',
  });
}
