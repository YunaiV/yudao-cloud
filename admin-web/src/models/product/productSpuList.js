import { message } from 'antd';
import { productSpuPage, productCategoryAdd, productCategoryUpdate, productCategoryUpdateStatus, productCategoryDelete } from '../../services/product';
import {routerRedux} from "dva/router";

export default {
  namespace: 'productSpuList',

  state: {
    list: [],
  },

  effects: {
    // *updateStatus({ payload }, { call, put }) {
    //   const { callback, body } = payload;
    //   const response = yield call(productCategoryUpdateStatus, body);
    //   if (callback) {
    //     callback(response);
    //   }
    //   yield put({
    //     type: 'tree',
    //     payload: {},
    //   });
    // },
    // *delete({ payload }, { call, put }) {
    //   const response = yield call(productCategoryDelete, payload);
    //   message.info('删除成功!');
    //   yield put({
    //     type: 'tree',
    //     payload: {},
    //   });
    // },
    *redirectToAdd({ payload }, { call, put }) {
      // const { callback, body } = payload;
      yield put(routerRedux.replace('/product/product-spu-add'));
    },
    *redirectToUpdate({ payload }, { call, put }) {
      // const { callback, body } = payload;
      debugger;
      yield put(routerRedux.replace('/product/product-spu-update?id=' + payload));
    },
    *page({ payload }, { call, put }) {
      // const { queryParams } = payload;
      const response = yield call(productSpuPage, payload);
      message.info('查询成功!');
      yield put({
        type: 'treeSuccess',
        payload: {
          list: response.data,
        },
      });
    },
  },

  reducers: {
    treeSuccess(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    },
  },
};
