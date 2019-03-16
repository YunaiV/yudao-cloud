import { message } from 'antd';
import { productSpuPage, productCategoryAdd, productCategoryUpdate, productCategoryUpdateStatus, productCategoryDelete } from '../../services/product';

export default {
  namespace: 'productSpuList',

  state: {
    list: [],
  },

  effects: {
    *add({ payload }, { call, put }) {
      const { callback, body } = payload;
      const response = yield call(productCategoryAdd, body);
      if (callback) {
        callback(response);
      }
      yield put({
        type: 'tree',
        payload: {},
      });
    },
    *update({ payload }, { call, put }) {
      const { callback, body } = payload;
      const response = yield call(productCategoryUpdate, body);
      if (callback) {
        callback(response);
      }
      yield put({
        type: 'tree',
        payload: {},
      });
    },
    *updateStatus({ payload }, { call, put }) {
      const { callback, body } = payload;
      const response = yield call(productCategoryUpdateStatus, body);
      if (callback) {
        callback(response);
      }
      yield put({
        type: 'tree',
        payload: {},
      });
    },
    *delete({ payload }, { call, put }) {
      const response = yield call(productCategoryDelete, payload);
      message.info('删除成功!');
      yield put({
        type: 'tree',
        payload: {},
      });
    },
    *page({ payload }, { call, put }) {
      const { queryParams } = payload;
      const response = yield call(productSpuPage, queryParams);
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
