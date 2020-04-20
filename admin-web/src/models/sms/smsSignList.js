import { addSign, deletedSign, pageSign, updateSign } from '../../services/sms';

export default {
  namespace: 'smsSignList',

  state: {
    total: 0,
    index: 1,
    size: 20,
    list: [],
  },

  effects: {
    *page({ payload }, { call, put }) {
      const response = yield call(pageSign, payload);
      if (response.code === 0) {
        yield put({
          type: 'pageSuccess',
          payload: {
            data: response.data,
            params: payload,
          },
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
      const { data, params } = payload;
      const { pageNo, pageSize } = params;
      const { list, total } = data;
      return {
        ...state,
        size: pageSize,
        index: pageNo,
        total,
        list,
      };
    },
  },
};
