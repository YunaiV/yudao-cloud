/* eslint-disable */

// localStorage 操作

const cacheKeys = {
  accessTokenKey: 'accessToken',
  refreshTokenKey: 'refreshToken',
};

///
/// 设置 loginToken，分为 accessToken 和 refreshToken

export function setLoginToken(accessToken, refreshToken) {
  setLocalStorage(cacheKeys.accessTokenKey, accessToken);
  setLocalStorage(cacheKeys.refreshTokenKey, refreshToken);
}

export function getLoginToken() {
  const res = {};
  cacheKeys[cacheKeys.accessTokenKey] = getLocalStorage(cacheKeys.accessTokenKey);
  cacheKeys[cacheKeys.refreshTokenKey] = getLocalStorage(cacheKeys.refreshTokenKey);
  return res;
}

///
/// 设置 localStorage 公共方法

function setLocalStorage(key, value) {
  try {
    localStorage.setItem(key, value);
  } catch (e) {
    throw new Error(`localStorage 设置错误! ${e}`);
  }
}

function getLocalStorage(key) {
  try {
    return localStorage.getItem(key);
  } catch (e) {
    throw new Error(`localStorage 获取错误! ${e}`);
  }
}
