import { message } from 'antd';
import { orderPage, updateOrderItem } from '../../services/order';

export default {
  namespace: 'orderList',

  state: {
    list: {
      pagination: {
        current: 0,
        pageSize: 10,
        total: 0,
      },
      dataSource: [],
    },
  },

  effects: {
    *queryPage({ payload }, { call, put }) {
      const response = yield call(orderPage, payload);
      message.info('查询成功!', response);
      const { total, orders } = response.data;
      yield put({
        type: 'queryPageSuccess',
        payload: {
          list: {
            dataSource: orders,
            pagination: {
              total,
              current: payload.pageNo,
              pageSize: payload.pageSize,
            },
          },
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
      const { list } = payload;
      return {
        ...state,
        list,
      };
    },
  },
};
