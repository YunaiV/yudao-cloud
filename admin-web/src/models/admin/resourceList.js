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
      value: `${item.displayName}-${item.id}`,
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
      yield call(deleteResource, payload);
      message.info('删除成功!');
      yield put({
        type: 'tree',
        payload: {},
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
      const treeData = buildSelectTree(resultData);

      // value 要保护 displayName 不然，搜索会失效
      const rootNode = [
        {
          title: '根节点',
          value: `根节点-0`,
          key: 0,
          children: [],
        },
      ];

      const selectTree = rootNode.concat(treeData);
      return {
        ...state,
        list: resultData,
        selectTree,
      };
    },
  },
};
