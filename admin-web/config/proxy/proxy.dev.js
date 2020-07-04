// 开发环境，的代理配置

module.exports = {
  '/management-api/': {
    target: 'http://127.0.0.1:18083/',
    // target: 'http://180.167.213.26:18083/',
    changeOrigin: true,
    pathRewrite: {},
  },
};
