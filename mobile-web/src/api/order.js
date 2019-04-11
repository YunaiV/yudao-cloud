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
