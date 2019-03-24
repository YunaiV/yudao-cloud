import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// order
export async function orderPage(params) {
  return request(`/order-api/admins/order/page?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function updateOrderItem(params) {
  return request(`/order-api/admins/order_item/update?${stringify(params)}`, {
    method: 'PUT',
    body: {
      ...params,
    },
  });
}
