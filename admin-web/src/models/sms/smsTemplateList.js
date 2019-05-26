import { pageTemplate, addTemplate, updateTemplate, deletedTemplate } from '../../services/sms';

export default {
  namespace: 'smsTemplateList',

  state: {
    list: [],
  },

  effects: {
    *page({ payload }, { call, put }) {
      const response = yield call(pageTemplate, payload);
      if (response.code === 0) {
        yield put({
          type: 'pageSuccess',
          payload: response.data,
        });
      }
    },
    *add({ payload }, { call }) {
      const { params, callback } = payload;
      const response = yield call(addTemplate, params);
      if (response.code === 0) {
        if (callback) {
          callback(response);
        }
      }
    },
    *update({ payload }, { call }) {
      const { params, callback } = payload;
      const response = yield call(updateTemplate, params);
      if (response.code === 0) {
        if (callback) {
          callback(response);
        }
      }
    },
    *deleted({ payload }, { call }) {
      const { params, callback } = payload;
      const response = yield call(deletedTemplate, params);
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
