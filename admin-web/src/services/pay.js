import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';

// Transaction

export async function queryPayTransactionPage(params) {
  return request(`/pay-api/admins/transaction/page?${stringify(params)}`, {
    method: 'GET',
  });
}

// Refund

export async function queryPayRefundPage(params) {
  return request(`/pay-api/admins/refund/page?${stringify(params)}`, {
    method: 'GET',
  });
}
