import request from "../config/request";

// order

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

export function getOrderConfirmCreateOrder(skuId, quantity, couponCardId) {
  return request({
    url: '/order-api/users/order/confirm_create_order',
    method: 'get',
    params: {
      skuId,
      quantity,
      couponCardId,
    }
  });
}

export function getOrderInfo(orderId) {
  return request({
    url: '/order-api/users/order/info',
    method: 'get',
    params: {
      orderId,
    }
  });
}

// Cart

export function createOrderFromCart(userAddressId, couponCardId, remark) {
  return request({
    url: '/order-api/users/order/create_order_from_cart',
    method: 'post',
    params: {
      userAddressId,
      remark,
      couponCardId,
    }
  });
}

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

export function listCart() {
  return request({
    url: '/order-api/users/cart/list',
    method: 'get',
    params: {
    }
  });
}

export function updateCartSelected(skuIds, selected) {
  return request({
    url: '/order-api/users/cart/update_selected',
    method: 'post',
    params: {
      skuIds: skuIds.join(','),
      selected,
    }
  });
}

export function getCartConfirmCreateOrder(couponCardId) {
  return request({
    url: '/order-api/users/cart/confirm_create_order',
    method: 'get',
    params: {
      couponCardId
    }
  });
}

export function getCartCalcSkuPrice(skuId) {
  return request({
    url: '/order-api/users/cart/calc_sku_price',
    method: 'get',
    params: {
      skuId,
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
