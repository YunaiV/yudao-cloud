import memoizeOne from 'memoize-one';
import isEqual from 'lodash/isEqual';
import { formatMessage } from 'umi/locale';
import Authorized from '@/utils/Authorized';
import { menu } from '../defaultSettings';
import { authorizationMenuResourceTree, authorizationResourcePermissions } from '../services/system';

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

// 用于生成uuid
function S4() {
  return ((1 + Math.random()) * 0x10000 || 0).toString(16).substring(1);
}
function guid() {
  return S4() + S4() + S4() + S4() + S4() + S4() + S4() + S4();
}

const findRootMenu = (antDataMenus, rootAntDataMenu, requestDataMenu) => {
  let res;
  for (let i = 0; i < antDataMenus.length; i += 1) {
    const antDataMenu = antDataMenus[i];
    if (antDataMenu.path === requestDataMenu.route) {
      res = rootAntDataMenu;
      break;
    }
    if (antDataMenu.children) {
      res = findRootMenu(antDataMenu.children, antDataMenu, requestDataMenu);
      break;
    }
  }
  return res;
};

const buildTreeMenu = (antMenuData, moveChildrenMenusData, requestDataMenus) => {
  return requestDataMenus.map(item => {
    if (!item.route) {
      // root 节点
      const uuid = `sms${guid()}`;
      const res = {
        icon: 'user',
        name: item.name,
        path: uuid,
      };

      // 子节点
      if (item.children) {
        // 通过子节点找到对于的父节点，设置 path，没有则是 uuid
        const rootMenu = findRootMenu(antMenuData, {}, item.children[0]);
        if (rootMenu) {
          res.path = rootMenu.path;
        }

        // 开始递归构建数据结构
        const childrenMenus = buildTreeMenu(antMenuData, moveChildrenMenusData, item.children);
        res.children = childrenMenus;
      }
      return res;
    }

    // moveChildrenMenusData 是一个 map，对比 url 地址是否存在，不存在就给一个 404 的页面
    const handleMapperData = moveChildrenMenusData[item.route];
    if (handleMapperData) {
      return {
        ...handleMapperData,
        icon: 'user',
        name: item.name,
        path: item.route,
      };
    }

    // 没有就返回404页面
    return moveChildrenMenusData['/exception/404'];
  });
};

const moveChildrenMenus = antDataMenus => {
  let res = {};
  for (let i = 0; i < antDataMenus.length; i += 1) {
    const antDataMenu = antDataMenus[i];
    res[antDataMenu.path] = {
      ...res,
      ...antDataMenu,
    };

    if (antDataMenu.children) {
      const childrenMenus = moveChildrenMenus(antDataMenu.children);
      res = {
        ...res,
        ...childrenMenus,
      };
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
      const { data } = yield call(authorizationMenuResourceTree);
      const { routes, authority } = payload;

      // authority 已经不适用
      const antMenuData = filterMenuData(memoizeOneFormatter(routes, authority));
      let menuData = antMenuData;
      // const resultMenuData = data;
      if (data !== 'all') {
        const moveChildrenMenusData = moveChildrenMenus(antMenuData);
        const buildTreeMenuData = buildTreeMenu(antMenuData, moveChildrenMenusData, data);
        menuData = buildTreeMenuData;
      }

      // 生成 menu 和 router mapping
      const breadcrumbNameMap = memoizeOneGetBreadcrumbNameMap(menuData);
      yield put({
        type: 'save',
        payload: { menuData, breadcrumbNameMap },
      });
    },
    *getUrlsData(state, { put, call }) {
      const { data } = yield call(authorizationResourcePermissions);

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
