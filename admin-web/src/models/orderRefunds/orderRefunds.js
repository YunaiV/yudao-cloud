import { list } from '../../services/orderRefunds';

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
