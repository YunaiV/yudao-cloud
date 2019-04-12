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

export function confirmReceiving(orderId) {
  return request({
    url: '/order-api/users/order/confirm_receiving',
    method: 'post',
    params: {
      orderId,
    }
  });
}

export function getConfirmCreateOrder(skuId, quantity) {
  return request({
    url: '/order-api/users/order/confirm_create_order',
    method: 'get',
    params: {
      skuId,
      quantity,
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

// Cart

export function addCart(skuId, quantity) {
  return request({
    url: '/order-api/users/cart/add',
    method: 'post',
    params: {
      skuId,
      quantity,
    }
  });
}

export function countCart() {
  return request({
    url: '/order-api/users/cart/count',
    method: 'get',
    params: {
    }
  });
}

// 物流信息

export function getLogisticsInfo(params) {
  return request({
    url: '/order-api/users/order_logistics/logistics_info',
    method: 'get',
    params: {
      ...params,
    }
  });
}
