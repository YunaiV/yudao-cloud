import request from "../config/request";


export function getOrderPage(params) {
  return request({
    url: '/order-api/users/order/order_page',
    method: 'get',
    params: {
      ...params,
    }
  });
}

export function cancelOrder(id) {
  return request({
    url: '/product-api/users/spu/info',
    method: 'get',
    params: {
      id,
    }
  });
}

export function createOrder(params) {
  return request({
    headers: {
      'Content-Type': 'application/json',
    },
    url: '/order-api/users/order/create_order',
    method: 'post',
    data: {
      ...params,
    },
  });
}

