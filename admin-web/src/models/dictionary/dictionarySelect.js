import { queryKey, queryText } from '../../services/dictionary';

export default {
  namespace: 'dictionarySelect',

  state: {
    list: [],
    text: '',
  },

  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(queryKey, payload);
      yield put({
        type: 'querySuccess',
        payload: {
          list: response.list,
        },
      });
    },
    *queryText({ payload }, { call, put }) {
      const response = yield call(queryText, payload);
      yield put({
        type: 'querySuccess',
        payload: {
          text: response.text,
        },
      });
    },
  },

  reducers: {
    querySuccess(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    },
  },
};
