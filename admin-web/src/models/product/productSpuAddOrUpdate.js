import { message } from 'antd';
import { productCategoryTree, productCategoryAdd, productCategoryUpdate, productCategoryUpdateStatus, productCategoryDelete } from '../../services/product';

export default {
  namespace: 'productSpuAddOrUpdate',

  state: {
    list: [],
    attrTree: [
      // {
      //   id: //
      //   name: //
      //   values: [{
      //      id: //
      //      name: //
      //   }]
      // }
    ]
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
    *addAttr({ payload }, { call, put }) {
      // const { queryParams } = payload;
      // const response = yield call(productCategoryTree, queryParams);
      message.info('调试：添加规格成功!');
      yield put({
        type: 'addAttrSuccess',
        payload: {
          attrAdd: {},
        },
      });
    },
    *selectAttr({ payload }, { call, put }) {
      // const { queryParams } = payload;
      // const response = yield call(productCategoryTree, queryParams);
      message.info('调试：添加规格成功!');
      yield put({
        type: 'selectAttrSuccess',
        payload: payload,
      });
    },
  },

  reducers: {
    addAttrSuccess(state, {payload}) {
      // debugger;
      // console.log(state.attrTree);
      state.attrTree.push(payload.attrAdd);
      return {
        ...state
      }
    },
    selectAttrSuccess(state, {payload}) {
      // debugger;
      // console.log(state.attrTree);
      state.attrTree[payload.attrIndex] = payload.attr;
      return {
        ...state
      }
    },
    treeSuccess(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    },
  },
};
