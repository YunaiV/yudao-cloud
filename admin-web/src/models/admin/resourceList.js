import { message } from 'antd';
import { addResource, updateResource, deleteResource, resourceTree } from '../../services/admin';

const buildSelectTree = list => {
  return list.map(item => {
    let children = [];
    if (item.children) {
      children = buildSelectTree(item.children);
    }
    return {
      title: item.displayName,
      value: item.displayName,
      key: item.id,
      children,
    };
  });
};

export default {
  namespace: 'resourceList',

  state: {
    list: [],
    selectTree: [],
  },

  effects: {
    *add({ payload }, { call, put }) {
      const { callback, body } = payload;
      const response = yield call(addResource, body);
      if (callback) {
        callback(response);
      }
      yield put({
        type: 'tree',
        payload: {},
      });
    },
    *update({ payload }, { call, put }) {
      const { callback, body } = payload;
      const response = yield call(updateResource, body);
      if (callback) {
        callback(response);
      }
      yield put({
        type: 'tree',
        payload: {},
      });
    },
    *delete({ payload }, { call, put }) {
      const response = yield call(deleteResource, payload);
      message.info('删除成功!');
      yield put({
        type: 'treeSuccess',
        payload: {
          list: response.data,
        },
      });
    },
    *tree({ payload }, { call, put }) {
      const { queryParams } = payload;
      const response = yield call(resourceTree, queryParams);
      message.info('查询成功!');
      yield put({
        type: 'treeSuccess',
        payload: response.data,
      });
    },
  },

  reducers: {
    treeSuccess(state, { payload }) {
      const resultData = payload;
      const selectTree = buildSelectTree(resultData);
      return {
        ...state,
        list: resultData,
        selectTree,
      };
    },
  },
};
