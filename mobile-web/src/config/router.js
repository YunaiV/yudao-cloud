import Vue from 'vue';
import Router from 'vue-router';

import { getAccessToken } from '../utils/cache';

Vue.use(Router);

const routes = [
  {
    path: '*',
    redirect: '/home'
  },
  {
    name: 'home',
    component: () => import('../page/index'),
    meta: {
      title: '首页'
    }
  },
  {
		path: '/login',
    component: () => import('../page/account/phonelogin'),
    meta: {
      title: '登录'
    }
  },
  // {
	// 	path: '/login/password',
  //   component: () => import('../page/account/password'),
  //   meta: {
  //     title: '登录'
  //   }
  // },
  // {
	// 	path: '/login/phone',
  //   component: () => import('../page/account/phonelogin'),
  //   meta: {
  //     title: '手机号登录'
  //   }
  // },
  // {
	// 	path: '/login/register',
  //   component: () => import('../page/account/register'),
  //   meta: {
  //     title: '注册'
  //   }
  // },
  {
		path: '/user/index',
    component: () => import('../page/user/index'),
    name: 'user',
    meta: {
      title: '会员中心'
    }
  },
  {
		path: '/user/info',
    component: () => import('../page/user/info/detail'),
    name: 'user',
    meta: {
      title: '个人信息'
    }
  },
  {
    path: '/user/address',
    component: () => import('../page/user/address/list'),
    meta: {
      title: '我的地址'
    }
  },
  {
    path: '/user/address/edit',
    component: () => import('../page/user/address/edit'),
    meta: {
      title: '修改地址'
    }
  },
  {
    path: '/user/favorite',
    component: () => import('../page/user/favorite/list'),
    meta: {
      title: '我的收藏'
    }
  },
  {
    path: '/user/coupon',
    component: () => import('../page/user/coupon/list'),
    meta: {
      title: '我的优惠券',
      requireAuth: true,
    }
  },
  {
    path: '/user/order',
    component: () => import('../page/user/order/list'),
    meta: {
      title: '我的订单'
    }
  },
  {
    path: '/user/order/:id',
    component: () => import('../page/user/order/list'),
    meta: {
      title: '我的订单'
    }
  },
  {
    path: '/user/order/info/:id',
    component: () => import('../page/user/order/info'),
    meta: {
      title: '我的订单'
    }
  },
  {
    path: '/user/order/logistics/:id',
    component: () => import('../page/user/order/logistics'),
    meta: {
      title: '订单追踪'
    }
  },
  {
    path: '/user/aftersale',
    component: () => import('../page/user/aftersale/list'),
    meta: {
      title: '售后'
    }
  },
  {
    path: '/user/aftersale/apply',
    component: () => import('../page/user/aftersale/apply'),
    meta: {
      title: '申请售后'
    }
  },
  {
    path: '/user/aftersale/detail',
    component: () => import('../page/user/aftersale/detail'),
    meta: {
      title: '服务单详情'
    }
  },
  {
    path: '/user/aftersale/track/:id',
    component: () => import('../page/user/aftersale/track'),
    meta: {
      title: '进度详情'
    }
  },
  {
    path: '/product/:id',
    component: () => import('../page/product/detail'),
    meta: {
      title: '商品详情'
    }
  },
  {
    path: '/product/search',
    component: () => import('../page/product/search'),
    meta: {
      title: '商品搜索'
    }
  },
  {
    path: '/products/list',
    component: () => import('../page/product/list'),
    meta: {
      title: '商品列表'
    }
  },
  {
    name: 'cart',
    component: () => import('../page/cart/index'),
    meta: {
      title: '购物车'
    }
  },
  {
    path: '/order',
    component: () => import('../page/shipping/order'),
    meta: {
      title: '确认订单'
    }
  },
  {
    path: '/order/success',
    component: () => import('../page/shipping/order-success'),
    meta: {
      title: '确认订单'
    }
  },
  {
    name: 'category',
    component: () => import('../page/category/index'),
    meta: {
      title: '分类'
    }
  },
  {
    path: '/coupon/fetch',
    component: () => import('../page/coupon/fetch'),
    meta: {
      title: '优惠劵领取'
    }
  }
];

// add route path
routes.forEach(route => {
  route.path = route.path || '/' + (route.name || '');
});

const router = new Router({ routes });

router.beforeEach((to, from, next) => {
  // 判断是否需要认证
  const requireAuth = to.meta && to.meta.requireAuth;
  if (requireAuth) {
    if (!getAccessToken()) { // 未登陆
      next({
        path: '/login',
        query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
      });
      return;
    }
  }
  // 处理标题
  const title = to.meta && to.meta.title;
  if (title) {
    document.title = title;
  }
  // 继续路由
  next();
});

export {
  router
};
