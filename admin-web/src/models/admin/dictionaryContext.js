import { dictionaryTree } from '../../services/admin';

export default {
  namespace: 'dictionaryContext',

  state: {
    dicTree: [],
    dicTreeMap: {},
  },

  effects: {
    *tree({ payload }, { call, put }) {
      const response = yield call(dictionaryTree, payload);
      const dicList = response.data;

      const dicTreeMap = {};
      dicList.map(item => {
        const dicKey = item.enumValue;
        const dicTreeItem = {};
        item.values.map(item2 => {
          dicTreeItem.text = item2.displayName;
          dicTreeItem.value = item2.value;
          return true;
        });
        dicTreeMap[dicKey] = dicTreeItem;
        return true;
      });

      yield put({
        type: 'treeSuccess',
        payload: {
          dicTree: dicList,
          dicTreeMap,
        },
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
  },
};
