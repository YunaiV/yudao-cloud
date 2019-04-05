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
    for (const key in object) {
      const val = object[key];
      if (
        new String(val).length > 0 &&
        val !== undefined &&
        val !== 'undefined' &&
        val !== null &&
        val !== 'null'
      ) {
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

export function arrayToStringParams(array) {
  let res = '';
  for (let i = 0; i < array.length; i++) {
    res += array[i];
    if (i < array.length - 1) {
      res += ',';
    }
  }
  return res;
}

export function stringify(params) {
  return qs.stringify(filterEmptyStr(params));
}

export default {
  ...qs,
  stringify,
};
