// 开发环境，的代理配置

export default {
  '/admin-api/': {
    // target: 'http://180.167.213.26:18083/',
    target: 'http://127.0.0.1:18083/',
    changeOrigin: true,
    pathRewrite: {},
  },
  '/product-api/': {
    // target: 'http://180.167.213.26:18083/',
    target: 'http://127.0.0.1:18081/',
    changeOrigin: true,
    pathRewrite: {},
  },
  '/order-api/': {
    target: 'http://127.0.0.1:18084/',
    changeOrigin: true,
    pathRewrite: {},
  },
  '/promotion-api/': {
    target: 'http://127.0.0.1:18085/',
    changeOrigin: true,
    pathRewrite: {},
  },
};
