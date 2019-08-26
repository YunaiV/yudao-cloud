export default [
  // user
  {
    path: '/user',
    component: '../layouts/UserLayout',
    routes: [
      { path: '/user', redirect: '/user/login' },
      { path: '/user/login', name: 'login', component: './User/Login' },
      { path: '/user/register', name: 'register', component: './User/Register' },
      {
        path: '/user/register-result',
        name: 'register.result',
        component: './User/RegisterResult',
      },
    ],
  },
  // app
  {
    path: '/',
    component: '../layouts/BasicLayout',
    Routes: ['src/pages/Authorized'],
    authority: ['admin', 'user'],
    routes: [
      // redirect
      { path: '/', redirect: '/home' },
      // home
      {
        path: '/home',
        name: 'home',
        icon: 'user',
        component: './Home/Home',
      },
      // admin
      {
        path: '/admin',
        name: 'admin',
        icon: 'user',
        routes: [
          {
            path: '/admin/admin-list',
            name: 'admin-list',
            component: './Admin/AdminList',
          },
          {
            path: '/admin/resource-list',
            name: 'resource-list',
            component: './Admin/ResourceList',
          },
          {
            path: '/admin/role-list',
            name: 'role-list',
            component: './Admin/RoleList',
          },
          {
            path: '/admin/dept-list',
            name: 'deptment-list',
            component: './Admin/DeptmentList',
          },
          {
            path: '/admin/dictionary-list',
            name: 'dictionary-list',
            component: './Admin/DictionaryList',
          },
        ],
      },
      // user
      {
        path: '/member', // TODO 芋艿，后面调整
        name: 'user',
        icon: 'user',
        routes: [
          {
            path: '/member/user-list',
            name: 'user-list',
            component: './User/UserList',
          },
        ],
      },
      // order
      {
        path: 'order',
        name: 'order',
        icon: 'user',
        routes: [
          {
            path: '/order/order-list',
            name: 'order-list',
            component: './Order/OrderList',
          },
          {
            path: '/order/order-refunds',
            name: 'order-refunds',
            component: './OrderRefunds/OrderRefundsList',
          },
        ],
      },
      // product
      {
        path: '/product',
        name: 'product',
        icon: 'user',
        routes: [
          {
            path: '/product/product-spu-list',
            name: 'product-spu-list',
            component: './Product/ProductSpuList',
          },
          {
            path: '/product/product-spu-add',
            name: 'product-spu-add',
            component: './Product/ProductSpuAddOrUpdate',
          },
          {
            path: '/product/product-spu-update',
            name: 'product-spu-update',
            component: './Product/ProductSpuAddOrUpdate',
          },
          {
            path: '/product/product-category-list',
            name: 'product-category-list',
            component: './Product/ProductCategoryList',
          },
          {
            path: '/product/product-brand-list',
            name: 'product-brand-list',
            component: './Product/ProductBrandList',
          },
          {
            path: '/product/product-attr-list',
            name: 'product-attr-list',
            component: './Product/ProductAttrList',
          },
        ],
      },
      // promotion
      {
        path: '/promotion',
        name: 'promotion',
        icon: 'user',
        routes: [
          {
            path: '/promotion/banner-list',
            name: 'promotion-banner-list',
            component: './Promotion/BannerList',
          },
          {
            path: '/promotion/product-recommend-list',
            name: 'product-recommend-list',
            component: './Promotion/ProductRecommendList',
          },
          // {
          //   path: '/product/product-category-list',
          //   name: 'product-category-list',
          //   component: './Product/ProductCategoryList',
          // },
          {
            path: '/promotion/coupon-card-template-list',
            name: 'coupon-card-template-list',
            component: './Promotion/CouponCardTemplateList',
          },
          {
            path: '/promotion/time-limit-discount-list',
            name: 'time-limit-discount-list',
            component: './Promotion/TimeLimitedDiscountList',
          },
          {
            path: '/promotion/full-privilege-list',
            name: 'full-privilege-list',
            component: './Promotion/FullPrivilegeList',
          },
        ],
      },
      // pay
      {
        path: '/pay',
        name: 'pay',
        icon: 'user',
        routes: [
          {
            path: '/pay/transaction-list',
            name: 'pay-transaction-list',
            component: './Pay/PayTransactionList',
          },
          {
            path: '/pay/refund-list',
            name: 'pay-refund-list',
            component: './Pay/PayRefundList',
          },
        ],
      },
      // sms
      {
        path: '/sms',
        name: 'sms',
        icon: 'user',
        routes: [
          {
            path: '/sms/sign-list',
            name: 'sign-list',
            component: './Sms/SignList',
          },
          {
            path: '/sms/template-list',
            name: 'template-list',
            component: './Sms/TemplateList',
          },
        ],
      },
      {
        path: '/dashboard',
        name: 'dashboard',
        icon: 'dashboard',
        routes: [
          {
            path: '/dashboard/analysis',
            name: 'analysis',
            component: './Dashboard/Analysis',
          },
          {
            path: '/dashboard/monitor',
            name: 'monitor',
            component: './Dashboard/Monitor',
          },
          {
            path: '/dashboard/workplace',
            name: 'workplace',
            component: './Dashboard/Workplace',
          },
        ],
      },
      // forms
      {
        path: '/form',
        icon: 'form',
        name: 'form',
        routes: [
          {
            path: '/form/basic-form',
            name: 'basicform',
            component: './Forms/BasicForm',
          },
          {
            path: '/form/step-form',
            name: 'stepform',
            component: './Forms/StepForm',
            hideChildrenInMenu: true,
            routes: [
              {
                path: '/form/step-form',
                redirect: '/form/step-form/info',
              },
              {
                path: '/form/step-form/info',
                name: 'info',
                component: './Forms/StepForm/Step1',
              },
              {
                path: '/form/step-form/confirm',
                name: 'confirm',
                component: './Forms/StepForm/Step2',
              },
              {
                path: '/form/step-form/result',
                name: 'result',
                component: './Forms/StepForm/Step3',
              },
            ],
          },
          {
            path: '/form/advanced-form',
            name: 'advancedform',
            authority: ['admin'],
            component: './Forms/AdvancedForm',
          },
        ],
      },
      // list
      {
        path: '/list',
        icon: 'table',
        name: 'list',
        routes: [
          {
            path: '/list/table-list',
            name: 'searchtable',
            component: './List/TableList',
          },
          {
            path: '/list/basic-list',
            name: 'basiclist',
            component: './List/BasicList',
          },
          {
            path: '/list/card-list',
            name: 'cardlist',
            component: './List/CardList',
          },
          {
            path: '/list/search',
            name: 'searchlist',
            component: './List/List',
            routes: [
              {
                path: '/list/search',
                redirect: '/list/search/articles',
              },
              {
                path: '/list/search/articles',
                name: 'articles',
                component: './List/Articles',
              },
              {
                path: '/list/search/projects',
                name: 'projects',
                component: './List/Projects',
              },
              {
                path: '/list/search/applications',
                name: 'applications',
                component: './List/Applications',
              },
            ],
          },
        ],
      },
      {
        path: '/profile',
        name: 'profile',
        icon: 'profile',
        routes: [
          // profile
          {
            path: '/profile/basic',
            name: 'basic',
            component: './Profile/BasicProfile',
          },
          {
            path: '/profile/basic/:id',
            name: 'basic',
            hideInMenu: true,
            component: './Profile/BasicProfile',
          },
          {
            path: '/profile/advanced',
            name: 'advanced',
            authority: ['admin'],
            component: './Profile/AdvancedProfile',
          },
        ],
      },
      {
        name: 'result',
        icon: 'check-circle-o',
        path: '/result',
        routes: [
          // result
          {
            path: '/result/success',
            name: 'success',
            component: './Result/Success',
          },
          { path: '/result/fail', name: 'fail', component: './Result/Error' },
        ],
      },
      {
        name: 'exception',
        icon: 'warning',
        path: '/exception',
        routes: [
          // exception
          {
            path: '/exception/403',
            name: 'not-permission',
            component: './Exception/403',
          },
          {
            path: '/exception/404',
            name: 'not-find',
            component: './Exception/404',
          },
          {
            path: '/exception/500',
            name: 'server-error',
            component: './Exception/500',
          },
          {
            path: '/exception/trigger',
            name: 'trigger',
            hideInMenu: true,
            component: './Exception/TriggerException',
          },
        ],
      },
      {
        name: 'account',
        icon: 'user',
        path: '/account',
        routes: [
          {
            path: '/account/center',
            name: 'center',
            component: './Account/Center/Center',
            routes: [
              {
                path: '/account/center',
                redirect: '/account/center/articles',
              },
              {
                path: '/account/center/articles',
                component: './Account/Center/Articles',
              },
              {
                path: '/account/center/applications',
                component: './Account/Center/Applications',
              },
              {
                path: '/account/center/projects',
                component: './Account/Center/Projects',
              },
            ],
          },
          {
            path: '/account/settings',
            name: 'settings',
            component: './Account/Settings/Info',
            routes: [
              {
                path: '/account/settings',
                redirect: '/account/settings/base',
              },
              {
                path: '/account/settings/base',
                component: './Account/Settings/BaseView',
              },
              {
                path: '/account/settings/security',
                component: './Account/Settings/SecurityView',
              },
              {
                path: '/account/settings/binding',
                component: './Account/Settings/BindingView',
              },
              {
                path: '/account/settings/notification',
                component: './Account/Settings/NotificationView',
              },
            ],
          },
        ],
      },
      {
        component: '404',
      },
    ],
  },
];
