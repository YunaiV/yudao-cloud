import memoizeOne from 'memoize-one';
import isEqual from 'lodash/isEqual';
import { formatMessage } from 'umi/locale';
import Authorized from '@/utils/Authorized';
import { menu } from '../defaultSettings';
import { getAdminMenus, getAdminUrls } from '../services/admin';

const { check } = Authorized;

// Conversion router to menu.
function formatter(data, parentAuthority, parentName) {
  return data
    .map(item => {
      if (!item.name || !item.path) {
        return null;
      }

      let locale = 'menu';
      if (parentName) {
        locale = `${parentName}.${item.name}`;
      } else {
        locale = `menu.${item.name}`;
      }
      // if enableMenuLocale use item.name,
      // close menu international
      const name = menu.disableLocal
        ? item.name
        : formatMessage({ id: locale, defaultMessage: item.name });
      const result = {
        ...item,
        name,
        locale,
        authority: item.authority || parentAuthority,
      };
      if (item.routes) {
        const children = formatter(item.routes, item.authority, locale);
        // Reduce memory usage
        result.children = children;
      }
      delete result.routes;
      return result;
    })
    .filter(item => item);
}

const memoizeOneFormatter = memoizeOne(formatter, isEqual);

/**
 * get SubMenu or Item
 */
const getSubMenu = item => {
  // doc: add hideChildrenInMenu
  if (item.children && !item.hideChildrenInMenu && item.children.some(child => child.name)) {
    return {
      ...item,
      children: filterMenuData(item.children), // eslint-disable-line
    };
  }
  return item;
};

/**
 * filter menuData
 */
const filterMenuData = menuData => {
  if (!menuData) {
    return [];
  }
  return menuData
    .filter(item => item.name && !item.hideInMenu)
    .map(item => check(item.authority, getSubMenu(item)))
    .filter(item => item);
};

/**
 * 递归构建服务端，配置的菜单
 * @param resultMenuData
 */
const recursionBuildResultMenu = resultMenuData => {
  const res = {};
  for (let i = 0; i < resultMenuData.length; i += 1) {
    const menuItem = resultMenuData[i];
    // 存在子节点
    res[menuItem.handler] = {
      ...menuItem,
    };
    if (menuItem.children) {
      res[menuItem.handler].children = recursionBuildResultMenu(menuItem.children);
    }
  }
  return res;
};

/**
 * 获取面包屑映射
 * @param {Object} menuData 菜单配置
 */
const getBreadcrumbNameMap = menuData => {
  const routerMap = {};

  const flattenMenuData = data => {
    data.forEach(menuItem => {
      if (menuItem.children) {
        flattenMenuData(menuItem.children);
      }
      // Reduce memory usage
      routerMap[menuItem.path] = menuItem;
    });
  };
  flattenMenuData(menuData);
  return routerMap;
};

const memoizeOneGetBreadcrumbNameMap = memoizeOne(getBreadcrumbNameMap, isEqual);

export default {
  namespace: 'menu',

  state: {
    menuData: [],
    urlsData: {},
    breadcrumbNameMap: {},
  },

  effects: {
    *getMenuData({ payload }, { put, call }) {
      const { data } = yield call(getAdminMenus);
      const { routes, authority } = payload;
      // authority 已经不适用
      const antMenuData = filterMenuData(memoizeOneFormatter(routes, authority));

      let menuData = antMenuData;
      const resultMenuData = data;
      if (data !== 'all') {
        // 处理后台数据结构
        const buildResultMenu = recursionBuildResultMenu(resultMenuData);
        // 过滤没有权限的菜单
        menuData = antMenuData.filter(item => {
          if (buildResultMenu[item.path]) {
            return item;
          }
          return false;
        });
      }

      // 生成 menu 和 router mapping
      const breadcrumbNameMap = memoizeOneGetBreadcrumbNameMap(menuData);
      yield put({
        type: 'save',
        payload: { menuData, breadcrumbNameMap },
      });
    },
    *getUrlsData(state, { put, call }) {
      const { data } = yield call(getAdminUrls);

      // 构建 {'/user': true} 这种 map 结构方便取数据、
      const urlsData = {};
      data.forEach(item => {
        urlsData[item] = true;
      });

      yield put({
        type: 'save',
        payload: { urlsData },
      });
    },
  },

  reducers: {
    save(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    },
  },
};
