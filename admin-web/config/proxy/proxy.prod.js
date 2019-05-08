// production 配置文件

export default {
  '/admin-api/': {
    target: 'http://api.shop.iocoder.cn:18099/admin-api',
    changeOrigin: true,
    pathRewrite: {},
  },
  '/product-api/': {
    target: 'http://api.shop.iocoder.cn:18099/product-api',
    changeOrigin: true,
    pathRewrite: {},
  },
  '/order-api/': {
    target: 'http://api.shop.iocoder.cn:18099/order-api',
    changeOrigin: true,
    pathRewrite: {},
  },
  '/promotion-api/': {
    target: 'http://api.shop.iocoder.cn:18099/promotion-api',
    changeOrigin: true,
    pathRewrite: {},
  },
  '/pay-api/': {
    target: 'http://api.shop.iocoder.cn:18099/pay-api',
    changeOrigin: true,
    pathRewrite: {},
  },
  '/user-api/': {
    target: 'http://api.shop.iocoder.cn:18099/user-api',
    changeOrigin: true,
    pathRewrite: {},
  },
  '/server/api/': {
    target: 'https://preview.pro.ant.design/',
    changeOrigin: true,
    pathRewrite: { '^/server': '' },
  },
};
