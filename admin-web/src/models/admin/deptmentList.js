import { message } from 'antd';
import { deptTreePage } from '../../services/admin';

export default {
  namespace: 'deptmentList',

  state: {
    list: [],
    selectTree: [],
    deptmentData: {
      list: [],
    },
  },

  effects: {
    *getDeptmentList({ payload }, { call, put }) {
      const result = yield call(deptTreePage, payload);
      let deptmentData = {};
      if (result.code === 0) {
        deptmentData = result.data;
      }
      yield put({
        type: 'save',
        payload: {
          deptmentData,
        },
      });
    },
  },

  reducers: {
    save(state, action) {
      return {
        ...state,
        ...action.payload,
      };
    },

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
