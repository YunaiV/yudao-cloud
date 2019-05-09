import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// order
export async function list(params) {
  return request(`/order-api/admins/order_return/list?${stringify(params)}`, {
    method: 'GET',
  });
}

export async function agree(params) {
  return request(`/order-api/admins/order_return/agree?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function refuse(params) {
  return request(`/order-api/admins/order_return/refuse?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function confirmReceipt(params) {
  return request(`/order-api/admins/order_return/confirm_receipt?${stringify(params)}`, {
    method: 'POST',
  });
}

export async function confirmRefund(params) {
  return request(`/order-api/admins/order_return/confirm_refund?${stringify(params)}`, {
    method: 'POST',
  });
}
