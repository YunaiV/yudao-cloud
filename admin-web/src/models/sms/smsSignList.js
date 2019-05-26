import { pageSign, addSign, updateSign, deletedSign } from '../../services/sms';

export default {
  namespace: 'smsSignList',

  state: {
    list: [],
  },

  effects: {
    *page({ payload }, { call, put }) {
      const response = yield call(pageSign, payload);
      if (response.code === 0) {
        yield put({
          type: 'pageSuccess',
          payload: response.data,
        });
      }
    },
    *add({ payload }, { call }) {
      const { params, callback } = payload;
      const response = yield call(addSign, params);
      if (response.code === 0) {
        if (callback) {
          callback(response);
        }
      }
    },
    *update({ payload }, { call }) {
      const { params, callback } = payload;
      const response = yield call(updateSign, params);
      if (response.code === 0) {
        if (callback) {
          callback(response);
        }
      }
    },
    *deleted({ payload }, { call }) {
      const { params, callback } = payload;
      const response = yield call(deletedSign, params);
      if (callback) {
        callback(response);
      }
    },
  },

  reducers: {
    pageSuccess(state, { payload }) {
      const { data } = payload;
      return {
        ...state,
        list: data,
      };
    },
  },
};
