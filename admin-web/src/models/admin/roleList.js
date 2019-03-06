import { message } from 'antd';
import { addRole, updateRole, deleteRole, queryRole } from '../../services/admin';

export default {
  namespace: 'roleList',

  state: {
    list: [],
    count: 0,
    pageNo: 0,
    pageSize: 10,
  },

  effects: {
    *add({ payload }, { call, put }) {
      const { callback, body, queryParams } = payload;
      const response = yield call(addRole, body);
      if (callback) {
        callback(response);
      }
      yield put({
        type: 'query',
        payload: {
          ...queryParams,
        },
      });
    },
    *update({ payload }, { call, put }) {
      const { callback, body, queryParams } = payload;
      const response = yield call(updateRole, body);
      if (callback) {
        callback(response);
      }
      yield put({
        type: 'query',
        payload: {
          ...queryParams,
        },
      });
    },
    *delete({ payload }, { call, put }) {
      const { queryParams, body } = payload;
      yield call(deleteRole, body);
      message.info('删除成功!');
      yield put({
        type: 'query',
        payload: {
          ...queryParams,
        },
      });
    },
    *query({ payload }, { call, put }) {
      const response = yield call(queryRole, payload);
      message.info('查询成功!');
      const { count, roles } = response.data;
      yield put({
        type: 'querySuccess',
        payload: {
          list: roles,
          count,
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
