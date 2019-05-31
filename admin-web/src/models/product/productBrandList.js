import { message } from 'antd';
import { productBrandPage} from '../../services/product';
import {routerRedux} from "dva/router";
import PaginationHelper from '../../../helpers/PaginationHelper';

const SEARCH_PARAMS_DEFAULT = {
  name: '',
  status: 1,
  cid: undefined,
};

export default {
  namespace: 'productBrandList',

  state: {
    // 分页列表相关
    list: [],
    listLoading: false,
    pagination: PaginationHelper.defaultPaginationConfig,
    searchParams: SEARCH_PARAMS_DEFAULT,

    // 添加 or 修改表单相关
    // modalVisible: false,
    // modalType: undefined, // 'add' or 'update' 表单
    formVals: {}, // 当前表单值
    // modalLoading: false,


    sortModalVisible: false, // 修改排序弹窗
    sortModalLoading: false, // 修改排序的加载
  },

  effects: {
    // *updateStatus({ payload }, { call, put }) {
    //   const { callback, body } = payload;
    //   const response = yield call(productCategoryUpdateStatus, body);
    //   if (callback) {
    //     callback(response);
    //   }
    //   yield put({
    //     type: 'tree',
    //     payload: {},
    //   });
    // },
    // *delete({ payload }, { call, put }) {
    //   const response = yield call(productCategoryDelete, payload);
    //   message.info('删除成功!');
    //   yield put({
    //     type: 'tree',
    //     payload: {},
    //   });
    // },
    *redirectToAdd({ payload }, { call, put }) {
      // const { callback, body } = payload;
      yield put(routerRedux.replace('/product/product-spu-add'));
    },
    *redirectToUpdate({ payload }, { call, put }) {
      // const { callback, body } = payload;
      yield put(routerRedux.replace('/product/product-spu-update?id=' + payload));
    },
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
      const response = yield call(productBrandPage, payload);
      // 响应
      yield put({
        type: 'add',
        payload: {
          list: response.data.brands,
          pagination: PaginationHelper.formatPagination(response.data, payload),
          searchParams: {
            name: payload.name,
            status: payload.status,
            cid: payload.cid,
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
    changeSortModalLoading(state, { payload }) {
      return {
        ...state,
        sortModalLoading: payload,
      };
    },
    changeListLoading(state, { payload }) {
      return {
        ...state,
        listLoading: payload,
      };
    },
    // 设置所有属性
    add(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    }
  },
};
