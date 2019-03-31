import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// order
export async function orderPage(params) {
  return request(`/order-api/admins/order/page?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function updateOrderItemPayAmount(params) {
  return request(`/order-api/admins/order/order_item/update_pay_amount?${stringify(params)}`, {
    method: 'PUT',
  });
}

export async function updateRemark(params) {
  return request(`/order-api/admins/order/update_remark?${stringify(params)}`, {
    method: 'PUT',
  });
}

export async function cancelOrder(params) {
  return request(`/order-api/admins/order/cancel_order?${stringify(params)}`, {
    method: 'PUT',
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

export async function getLogistics(params) {
  return request(`/order-api/admins/order_item/update?${stringify(params)}`, {
    method: 'PUT',
    body: {
      ...params,
    },
  });
}
