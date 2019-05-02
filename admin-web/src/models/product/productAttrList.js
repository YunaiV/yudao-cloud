import { message } from 'antd';
import { productAttrTree, productAttrValueAdd } from '../../services/product';

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
      // message.info('查询成功!');
      yield put({
        type: 'treeSuccess',
        payload: {
          tree: response.data,
        },
      });
    },
    *addValue({ payload, callback }, { call, put }) {
      // debugger;
      // const {queryParams} = payload;
      const response = yield call(productAttrValueAdd, payload);
      // message.info('查询成功!');
      // yield put({
      //   type: 'treeSuccess',
      //   payload: {
      //     tree: response.data,
      //   },
      // });
      if (response.code === 0) {
        // 刷新规格列表
        yield put({
          type: 'tree',
          payload: {},
        });
        // 回调方法
        if (callback) {
          callback(response.data);
        }
      }
    }
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
