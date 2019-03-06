import { message } from 'antd';
import {
  addAdmin,
  updateAdmin,
  updateAdminStatus,
  deleteAdmin,
  queryAdmin,
} from '../../services/admin';

export default {
  namespace: 'adminList',

  state: {
    list: [],
    count: 0,
    pageNo: 0,
    pageSize: 10,
  },

  effects: {
    *add({ payload }, { call, put }) {
      const { callback, body, queryParams } = payload;
      const response = yield call(addAdmin, body);
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
      const response = yield call(updateAdmin, body);
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
    *updateStatus({ payload }, { call, put }) {
      const { body, queryParams } = payload;
      yield call(updateAdminStatus, body);
      message.info('更新成功!');
      yield put({
        type: 'query',
        payload: {
          ...queryParams,
        },
      });
    },
    *delete({ payload }, { call, put }) {
      const { queryParams, body } = payload;
      yield call(deleteAdmin, body);
      message.info('删除成功!');
      yield put({
        type: 'query',
        payload: {
          ...queryParams,
        },
      });
    },
    *query({ payload }, { call, put }) {
      const response = yield call(queryAdmin, payload);
      message.info('查询成功!');
      const { count, admins } = response.data;
      yield put({
        type: 'querySuccess',
        payload: {
          list: admins,
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
