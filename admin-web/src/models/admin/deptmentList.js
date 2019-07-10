import { message } from 'antd';
import { deptTreePage, deptTreeAll, addDeptment, updateDeptment } from '../../services/admin';

const buildSelectTree = list => {
  return list.map(item => {
    let children = [];
    if (item.children) {
      children = buildSelectTree(item.children);
    }
    return {
      title: item.name,
      value: `${item.name}-${item.id}`,
      key: item.id,
      children,
    };
  });
};

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
    *add({ payload }, { call, put }) {
      const { onSuccess, body } = payload;
      const response = yield call(addDeptment, body);
      if (response && response.code === 0) {
        onSuccess && onSuccess();
      }
    },
    *update({ payload }, { call, put }) {
      const { onSuccess, body } = payload;
      const response = yield call(updateDeptment, body);
      if (response && response.code === 0) {
        onSuccess && onSuccess();
      }
    },
    *getDeptmentAll({ payload }, { call, put }) {
      const result = yield call(deptTreeAll, payload);
      yield put({
        type: 'treeSuccess',
        payload: result.data,
      });
    },
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
