import axios from 'axios'
import {baseUrl, dataSources} from './env';
import datas from '../data/data';
import { getAccessToken } from '../utils/cache.js';
import { Dialog } from 'vant';

const serviceRouter = function(requestUrl) {
  function getConfig() {
    const configDev = {
      '/order-api': {
        prefix: '/order-api',
        target: 'http://127.0.0.1:18088/order-api',
      },
      '/user-api': {
        prefix: '/user-api',
        target: 'http://127.0.0.1:18082/user-api',
      },
      '/product-api': {
        prefix: '/product-api',
        target: 'http://127.0.0.1:18081/product-api',
      },
      '/promotion-api': {
        prefix: '/promotion-api',
        target: 'http://127.0.0.1:18085/promotion-api',
      },
      '/pay-api': {
        prefix: '/pay-api',
        target: 'http://127.0.0.1:18084/pay-api',
      },
    };

    const configProd = {
      '/order-api': {
        prefix: '/order-api',
        target: 'http://api.shop.iocoder.cn:18088/order-api',
      },
      '/user-api': {
        prefix: '/user-api',
        target: 'http://api.shop.iocoder.cn:18099/user-api',
      },
      '/product-api': {
        prefix: '/product-api',
        target: 'http://api.shop.iocoder.cn:18099/product-api',
      },
      '/promotion-api': {
        prefix: '/promotion-api',
        target: 'http://api.shop.iocoder.cn:18099/promotion-api',
      },
      '/pay-api': {
        prefix: '/pay-api',
        target: 'http://api.shop.iocoder.cn:18099/pay-api',
      },
    };

    if (process.env.NODE_ENV == 'development') {
      return configDev;
    } else {
      return configProd
    }
  }

  // function doCreateServer(config) {
  //   // 获取请求配置文件
  //   const createServer = {};
  //   for (const configKey in config) {
  //     const serverPrefix = configKey;
  //     const {target} = config[configKey];
  //     // 创建服务
  //     createServer[serverPrefix] = axios.create({
  //       baseURL: target, // api 的 base_url
  //       timeout: 5000, // request timeout
  //       headers: {
  //         'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
  //       }
  //     });
  //   }
  // }

  const config = getConfig();
  // const createServer = doCreateServer(config);
  const indexOf = requestUrl.indexOf("/", 1);
  const _urlPrefix = requestUrl.substring(0,  indexOf);
  if (!config[_urlPrefix]) {
    // throw new Error(`服务路由，未找到可用服务! ${requestUrl}`);
    console.error(`服务路由，未找到可用服务! ${requestUrl}`)
    return ''
  }
  // if (!createServer[_urlPrefix]) {
  //   throw new Error("服务路由，未找到可用服务!");
  // }

  // const { target } = config[_urlPrefix];
  // const requestServer = createServer[_urlPrefix];
  // const targetRequestUrl = _requestUrl.replace(_urlPrefix, target)
  // return createServer;
  return config[_urlPrefix];
};

const service = axios.create({
  // baseURL: baseUrl, // api 的 base_url
  timeout: 5000, // request timeout
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
  }
});

const servicef = function (parameter) {
  // debugger;
  if (dataSources == 'local') {
    //定义回调函数和axios一致
    const promist = new Promise(function (resolve, reject) {
      var data = datas[parameter.url];
      if (typeof data == 'string') {
        data = JSON.parse(data);
      }
      resolve(data);
    });
    return promist;
  }
  // 设置 access token
  // debugger;
  // if (getAccessToken()) {
  //   parameter.headers = {
  //     ...parameter.headers,
  //     'Authorization': `Bearer ${getAccessToken()}`,
  //   };
  // }
  // debugger;

  return service(parameter);
};


service.interceptors.request.use(
  config => {
    // Do something before request is sent
    //   if (store.getters.token) {
    //     // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
    //     config.headers['X-Token'] = getToken()
    //   }

    // 切换地址
    const { target, prefix } = serviceRouter(config.url)
    let url = config.url = config.url.replace(`${prefix}`, target);
    // TODO 芋艿，这些 url 不用增加认证 token 。可能这么写，有点脏，后面看看咋优化下。
    if (url.indexOf('user-api/users/passport/mobile/send_register_code') !== -1
      || url.indexOf('user-api/users/passport/mobile/register') !== -1) {
      return config;
    }

    // debugger;
    if (getAccessToken()) {
      config.headers['Authorization'] = `Bearer ${getAccessToken()}`;
    }

    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
);

// response interceptor
service.interceptors.response.use(
  //response => response,
  /**
   * 下面的注释为通过在response里，自定义code来标示请求状态
   * 当code返回如下情况则说明权限有问题，登出并返回到登录页
   * 如想通过 xmlhttprequest 来状态码标识 逻辑可写在下面error中
   * 以下代码均为样例，请结合自生需求加以修改，若不需要，则可删除
   */
  response => {
    // debugger;
    const res = response.data;
    const code = res.code;
    if (code !== 0) {

      // // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
      // if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
      //   // 请自行在引入 MessageBox
      //   // import { Message, MessageBox } from 'element-ui'
      //   MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
      //     confirmButtonText: '重新登录',
      //     cancelButtonText: '取消',
      //     type: 'warning'
      //   }).then(() => {
      //     store.dispatch('FedLogOut').then(() => {
      //       location.reload() // 为了重新实例化vue-router对象 避免bug
      //     })
      //   })
      // }

      // TODO token 过期
      // TODO 需要拿 refresh token 置换
      if (code === 1001001012) {
        Dialog.confirm({
          title: '系统提示',
          message: res.message,
          confirmButtonText: '重新登陆',
          beforeClose: function (action, done) {
            done();
            if (action === 'confirm') {
              // debugger;
              // this.$router.push({ path: '/login' })
              // TODO 跳转到登陆页.不是很优雅
              location.replace('/#login');
              location.reload();
            }
          }
        });
      } else {
        Dialog.alert({
          title: '系统提示',
          message: res.message,
        });
      }
      return Promise.reject('error')
    } else {
      // if (typeof response.data.Tag == 'string') {
      //   return JSON.parse(response.data.Tag);
      // } else {
      //   return response.data.Tag;
      // }
      // debugger;
      return res.data;
    }
  },
  error => {
    Dialog.alert({
      title: '系统提示',
      message: error,
    });
    console.error(`请求失败`, error);
    throw new Error(error);
    // return Promise.reject(error)
  }
);


export default servicef
