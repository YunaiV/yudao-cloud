// 开发环境，的代理配置

export default {
  '/admin-api/': {
    target: 'http://180.167.213.26:18083/',
    changeOrigin: true,
    pathRewrite: {},
  },
  '/server/api/': {
    target: 'https://preview.pro.ant.design/',
    changeOrigin: true,
    pathRewrite: { '^/server': '' },
  },
};
