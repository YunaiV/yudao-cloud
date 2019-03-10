// development 和 production

import path from 'path';
import proxyDev from './proxy.dev';
import proxyProd from './proxy.prod';

function basePathBuilder(proxys, basePath) {
  for (const key in proxys) {
    const proxy = proxys[key];
    const pathRewrite = proxy.pathRewrite;
    const newPathRewrite = {};
    for (const key2 in pathRewrite) {
      const item = pathRewrite[key2];
      newPathRewrite[key2] = path.join(basePath, item);
    }
    proxy.pathRewrite = newPathRewrite;
  }
  return proxys;
}

export default function(NODE_ENV, basePath) {
  let proxys = NODE_ENV === 'development' || NODE_ENV === 'undefined' ? proxyDev : proxyProd;
  if (basePath) {
    proxys = basePathBuilder(proxys, basePath);
  }
  return proxys;
}
