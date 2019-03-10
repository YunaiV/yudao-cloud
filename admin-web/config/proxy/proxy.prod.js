// production 配置文件

export default {
  '/admin-api/': {
    target: 'http://180.167.213.26:18083/',
    changeOrigin: true,
    pathRewrite: {
      '^/admin-api': `/admin-web/admin-api`,
    },
  },
  '/server/api/': {
    target: 'https://preview.pro.ant.design/',
    changeOrigin: true,
    pathRewrite: { '^/server': '' },
  },
};
