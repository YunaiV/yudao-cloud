import { message } from 'antd';
import { orderPage, updateOrderItem } from '../../services/order';

export default {
  namespace: 'orderList',

  state: {
    list: [],
  },

  effects: {
    *queryPage({ payload }, { call, put }) {
      const response = yield call(orderPage, payload);
      message.info('查询成功!');
      yield put({
        type: 'queryPageSuccess',
        payload: {
          list: response.data,
        },
      });
    },
    *updateOrderItem({ payload }, { call, put }) {
      const { params } = payload;
      const response = yield call(updateOrderItem, params);
      message.info('查询成功!');
      yield put({
        type: 'queryPageSuccess',
        payload: {
          list: response.data,
        },
      });
    },
  },

  reducers: {
    queryPageSuccess(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    },
  },
};
