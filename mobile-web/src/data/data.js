
import success from './common/success.json'

import GetPage from './page/GetPage.json'
import PageProduct from './page/Product.json'

import GetUserIndex from './user/GetUserIndex.json'
import GetFavorite from './user/GetFavorite.json'

import GetAddressList from './user/GetAddressList.json'
import GetAddressById from './user/GetAddressById.json'

import GetCoupon from './user/GetCoupon.json'

export default {
    '/Page/GetPage':GetPage,

    '/Page/Product':PageProduct,
    


    '/User/GetUserIndex':GetUserIndex,
    '/User/GetFavorite':GetFavorite,
    '/User/DelFavorite':success,
    
    '/User/GetAddressList':GetAddressList,
    '/User/GetAddressById':GetAddressById,

    '/User/SaveAddress':success,
    '/User/DelAddress':success,
    
    '/User/GetCoupon':GetCoupon,
    '/User/ExchangeCoupon':success,
}