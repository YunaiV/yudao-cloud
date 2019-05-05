import { message } from 'antd';
import { productCategoryTree, productCategoryAdd, productCategoryUpdate, productCategoryUpdateStatus, productCategoryDelete } from '../../services/product';

export default {
  namespace: 'productCategoryList',

  state: {
    // 分页列表相关
    list: [],
    listLoading: false,

    // 添加 or 修改表单相关
    modalVisible: false,
    modalType: undefined, // 'add' or 'update' 表单
    formVals: {}, // 当前表单值
    modalLoading: false,

  },

  effects: {
    // 查询列表
    *tree({ payload }, { call, put }) {
      // 显示加载中
      yield put({
        type: 'changeListLoading',
        payload: true,
      });

      // 请求
      const { queryParams } = payload;
      // 响应
      const response = yield call(productCategoryTree, queryParams);
      yield put({
        type: 'treeSuccess',
        payload: {
          list: response.data,
        },
      });

      // 隐藏加载中
      yield put({
        type: 'changeListLoading',
        payload: false,
      });
    },
    *add({ payload }, { call, put }) {
      // 显示加载中
      yield put({
        type: 'changeModalLoading',
        payload: true,
      });

      // 请求
      const { callback, body } = payload;
      const response = yield call(productCategoryAdd, body);
      // 响应
      if (response.code === 0) {
        if (callback) {
          callback(response);
        }
        // 刷新列表
        yield put({
          type: 'tree',
          payload: {},
        });
      }

      // 隐藏加载中
      yield put({
        type: 'changeModalLoading',
        payload: false,
      });
    },
    *update({ payload }, { call, put }) {
      // 显示加载中
      yield put({
        type: 'changeModalLoading',
        payload: true,
      });

      // 请求
      const { callback, body } = payload;
      const response = yield call(productCategoryUpdate, body);
      // 响应
      if (response.code === 0) {
        if (callback) {
          callback(response);
        }
        // 刷新列表
        yield put({
          type: 'tree',
          payload: {},
        });
      }

      // 隐藏加载中
      yield put({
        type: 'changeModalLoading',
        payload: false,
      });
    },
    *updateStatus({ payload }, { call, put }) {
      // 请求
      const { callback, body } = payload;
      // 响应
      const response = yield call(productCategoryUpdateStatus, body);
      if(response.code === 0) {
        message.info('更新状态成功!');
        if (callback) {
          callback(response);
        }
        yield put({
          type: 'tree',
          payload: {},
        });
      }
    },
    *delete({ payload }, { call, put }) {
      // 响应
      const response = yield call(productCategoryDelete, payload);
      if(response.code === 0) {
        message.info('删除成功!');
        if (callback) {
          callback(response);
        }
        yield put({
          type: 'tree',
          payload: {},
        });
      }
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
    changeModalLoading(state, { payload }) {
      return {
        ...state,
        modalLoading: payload,
      };
    },
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
