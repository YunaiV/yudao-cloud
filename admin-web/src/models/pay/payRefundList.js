import { message } from 'antd';
import { productSpuPage, productSpuUpdateSort } from '../../services/product';
import {routerRedux} from "dva/router";
import PaginationHelper from '../../../helpers/PaginationHelper';
import {getPromotionActivityPage} from "../../services/promotion";
import {queryPayRefundPage, queryPayTransactionPage} from "../../services/pay";

const SEARCH_PARAMS_DEFAULT = {
  createBeginTime: undefined,
  createEndTime: undefined,
  finishBeginTime: undefined,
  finishEndTime: undefined,
  status: undefined,
  payChannel: undefined,
};

export default {
  namespace: 'payRefundList',

  state: {
    // 分页列表相关
    list: [],
    listLoading: false,
    pagination: PaginationHelper.defaultPaginationConfig,
    searchParams: SEARCH_PARAMS_DEFAULT,

    // 添加 or 修改表单相关
  },

  effects: {
    *page({ payload }, { call, put }) {
      // const { queryParams } = payload;
      // const response = yield call(productSpuPage, payload);
      // message.info('查询成功!');
      // yield put({
      //   type: 'treeSuccess',
      //   payload: {
      //     list: response.data,
      //   },
      // });

      // 显示加载中
      yield put({
        type: 'changeListLoading',
        payload: true,
      });

      // 请求
      const response = yield call(queryPayRefundPage, payload);
      // 响应
      yield put({
        type: 'setAll',
        payload: {
          list: response.data.list,
          pagination: PaginationHelper.formatPagination(response.data, payload),
          searchParams: {
            createBeginTime: payload.createBeginTime,
            createEndTime: payload.createEndTime,
            finishBeginTime: payload.finishBeginTime,
            finishEndTime: payload.finishEndTime,
            status: payload.status,
            payChannel: payload.payChannel,
          }
        },
      });

      // 隐藏加载中
      yield put({
        type: 'changeListLoading',
        payload: false,
      });
    },
  },

  reducers: {
    treeSuccess(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    },
    // 修改加载中的状态
    changeListLoading(state, { payload }) {
      return {
        ...state,
        listLoading: payload,
      };
    },
    // 设置所有属性
    setAll(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    }
  },
};
