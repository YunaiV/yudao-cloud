import { message } from 'antd';
import { list, agree, refuse, confirmReceipt, confirmRefund } from '../../services/orderRefunds';

export default {
  namespace: 'orderRefunds',

  state: {
    index: 0,
    totalCount: 0,
    pageSize: 20,
    list: [],
  },

  effects: {
    *list({ payload }, { call, put }) {
      const response = yield call(list, payload);
      yield put({
        type: 'listSuccess',
        payload: response.data,
      });
    },
    *agree({ payload }, { call }) {
      const { callback, params } = payload;
      const response = yield call(agree, params);
      if (response.code !== 0) {
        message.error('操作失败!');
      } else {
        message.success('操作成功!');
        if (callback) {
          callback(response);
        }
      }
    },
    *refuse({ payload }, { call }) {
      const { callback, params } = payload;
      const response = yield call(refuse, params);
      if (response.code !== 0) {
        message.error('操作失败!');
      } else {
        message.success('操作成功!');
        if (callback) {
          callback(response);
        }
      }
    },
    *confirmReceipt({ payload }, { call }) {
      const { callback, params } = payload;
      const response = yield call(confirmReceipt, params);
      if (response.code !== 0) {
        message.error('操作失败!');
      } else {
        message.success('操作成功!');
        if (callback) {
          callback(response);
        }
      }
    },
    *confirmRefund({ payload }, { call }) {
      const { callback, params } = payload;
      const response = yield call(confirmRefund, params);
      if (response.code !== 0) {
        message.error('操作失败!');
      } else {
        message.success('操作成功!');
        if (callback) {
          callback(response);
        }
      }
    },
  },

  reducers: {
    listSuccess(state, { payload }) {
      const { index, totalCount, pageSize, data } = payload;
      return {
        ...state,
        index,
        totalCount,
        pageSize,
        list: data,
      };
    },
  },
};
