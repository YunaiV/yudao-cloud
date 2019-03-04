import request from "../config/request";


export function GetUserIndex() {
  return request({
    url: '/User/GetUserIndex',
    method: 'get',
  })
}

export function GetFavorite(data){
  return request({
    url: '/User/GetFavorite',
    method: 'post',
    params: { data }
  })
}

export function DelFavorite(id){
  return request({
    url: '/User/DelFavorite',
    method: 'get',
    params: { id:id }
  })
}

export function GetAddressList(){
  return request({
    url: '/User/GetAddressList',
    method: 'get',
  })
}

export function GetAddressById(id){
  return request({
    url: '/User/GetAddressById',
    method: 'get',
    params: { id }
  })
}

export function SaveAddress(data){
  return request({
    url: '/User/SaveAddress',
    method: 'post',
    params: { data }
  })
}
export function DelAddress(data){
  return request({
    url: '/User/DelAddress',
    method: 'post',
    params: { data }
  })
}


export function GetCoupon(data){
  return request({
    url: '/User/GetCoupon',
    method: 'Post',
    params: { data }
  })
}

export function ExchangeCoupon(code){
  return request({
    url: '/User/ExchangeCoupon',
    method: 'Post',
    params: { code:code }
  })
}


  