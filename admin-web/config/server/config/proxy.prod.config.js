module.exports = {
  '/admin-api -> http://180.167.213.26:18083/': {
    changeOrigin: true,
    pathRewrite: {},
  },
  '/server/api -> https://preview.pro.ant.design/': {
    changeOrigin: true,
    pathRewrite: { '^/server': '' },
  },
};
