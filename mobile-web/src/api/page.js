import request from "../config/request";


export function GetPage() {
    return request({
        url: '/Page/GetPage',
        method: 'get',
    })
}

export function getProduct(id) {
    return request({
        url: '/Page/Product',
        method: 'get',
        params: {id}
    })
}