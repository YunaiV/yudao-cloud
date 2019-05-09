const proxy = require('http-proxy-middleware');
const ObjectAssign = require('object-assign');
const { chalkError, chalkSuccess } = require('../config/chalk.config');

/**
 *
 * key :
 *  [0]: /api
 *  [1]: target url
 *
 * 预计写法：
 *  1、 /api -> baidu.com : { }
 *  2、 /user : function() { return {} }
 *
 * @param config
 */
module.exports = function(config) {
  console.info(chalkSuccess('build proxy.%s.config 配置!'), process.env.NODE_ENV);

  const proxys = [];
  if (!config) {
    console.log(chalkError('proxy.%s.config 没有配置!'), process.env.NODE_ENV);
  }

  for (const key in config) {
    let source;
    let target;
    if (/->/.test(key)) {
      const keys = key.toString().split('->');
      source = keys[0].trim();
      target = keys[1].trim();
    } else {
      source = key;
      target = config[key].target;
    }

    if (typeof config !== 'object') {
      console.log(
        chalkError('%s: proxy.%s.config 初始化失败 config 类型为 object!'),
        key,
        process.env.NODE_ENV
      );
      break;
    }

    proxys.push(proxy(source, ObjectAssign({ target }, config[key])));
  }

  return proxys;
};
