/* eslint-disable */
import qs from 'qs';

/**
 * 过滤字符串为 '' 直接不传了
 *
 * @param params
 */
function filterEmptyStr(params) {
  function filterObject(object) {
    const res = {};
    for (const key in params) {
      const val = params[key];
      if (typeof val === 'string' && val) {
        res[key] = val;
      } else {
        res[key] = val;
      }
    }
    return res;
  }

  if (typeof params === 'object') {
    return filterObject(params);
  } else if (params instanceof Array) {
    let res = [];
    for (const object in params) {
      res.push(filterObject(params));
    }
  }
}

export function stringify(params) {
  return qs.stringify(filterEmptyStr(params));
}

export default qs;
