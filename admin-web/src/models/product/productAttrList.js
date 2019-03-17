import { message } from 'antd';
import { productSpuPage, productAttrTree, productCategoryUpdate, productCategoryUpdateStatus, productCategoryDelete } from '../../services/product';

export default {
  namespace: 'productAttrList',

  state: {
    list: [],
  },

  effects: {
    // *add({ payload }, { call, put }) {
    //   const { callback, body } = payload;
    //   const response = yield call(productCategoryAdd, body);
    //   if (callback) {
    //     callback(response);
    //   }
    //   yield put({
    //     type: 'tree',
    //     payload: {},
    //   });
    // },
    // *update({ payload }, { call, put }) {
    //   const { callback, body } = payload;
    //   const response = yield call(productCategoryUpdate, body);
    //   if (callback) {
    //     callback(response);
    //   }
    //   yield put({
    //     type: 'tree',
    //     payload: {},
    //   });
    // },
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

    *tree({ payload }, { call, put }) {
      const { queryParams } = payload;
      const response = yield call(productAttrTree, queryParams);
      message.info('查询成功!');
      yield put({
        type: 'treeSuccess',
        payload: {
          tree: response.data,
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
