import { message } from 'antd';
import { orderItems, getOrderRecipientInfo, orderDeliver } from '../../services/order';

export default {
  namespace: 'orderDelivery',

  state: {
    orderId: null,
    visible: false,
    list: [],
    orderRecipient: {},
    selectedRowKeys: [],
  },

  effects: {
    *getOrderItems({ payload }, { call, put }) {
      const response1 = yield call(orderItems, payload);
      const response2 = yield call(getOrderRecipientInfo, payload);

      yield put({
        type: 'getOrderItemsSuccess',
        payload: {
          list: response1.data,
          orderRecipient: response2.data,
        },
      });
    },
    *deliver({ payload }, { call, put }) {
      const { code } = yield call(orderDeliver, payload);
      if (code === 0) {
        message.info('发货成功!');
        yield put({
          type: 'changeVisible',
          payload: {
            visible: false,
          },
        });
      }
    },
  },

  reducers: {
    getOrderItemsSuccess(state, { payload }) {
      const { list, orderRecipient } = payload;
      return {
        ...state,
        list,
        orderRecipient,
      };
    },
    changeVisible(state, { payload }) {
      const { visible, orderId } = payload;
      return {
        ...state,
        visible,
        orderId,
      };
    },
    changeSelectedRowKeys(state, { payload }) {
      const { selectedRowKeys } = payload;
      return {
        ...state,
        selectedRowKeys,
      };
    },
  },
};
