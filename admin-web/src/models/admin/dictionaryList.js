import {message} from 'antd';
import {dictionaryAdd, dictionaryDelete, dictionaryList, dictionaryUpdate} from '../../services/admin';

export default {
  namespace: 'dictionaryList',

  state: {
    list: [],
  },

  effects: {
    *add({ payload }, { call, put }) {
      const { callback, body } = payload;
      const response = yield call(dictionaryAdd, body);
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
      const response = yield call(dictionaryUpdate, body);
      if (callback) {
        callback(response);
      }
      yield put({
        type: 'tree',
        payload: {},
      });
    },
    *delete({ payload }, { call, put }) {
      const response = yield call(dictionaryDelete, payload);
      message.info('删除成功!');
      // yield put({
      //   type: 'treeSuccess',
      //   payload: {
      //     list: response.data,
      //   },
      // });
      yield put({
        type: 'tree',
        payload: {},
      });
    },
    *tree({ payload }, { call, put }) {
      const { queryParams } = payload;
      const response = yield call(dictionaryList, queryParams);
      message.info('查询成功!');

      // 将数据格式化成 tree 格式
      // debugger;
      let treeNodeMap = new Map(); // key: enumValue value: Node
      for(let i = 0, len = response.data.length; i < len; i++){
        let dataDict = response.data[i];
        let treeNode = treeNodeMap.get(dataDict.enumValue);
        if (!treeNode) {
            treeNode = {
                enumValue: dataDict.enumValue,
                children: [dataDict]
            };
            treeNodeMap.set(dataDict.enumValue, treeNode);
            treeNode.index = dataDict.enumValue; // 因为 Table 必须要有 rowKey ，所以这里需要处理下。主要是数据字典的结构特殊
        } else {
            treeNode.children.push(dataDict);
        }
        dataDict.index = dataDict.id; // 因为 Table 必须要有 rowKey ，所以这里需要处理下。主要是数据字典的结构特殊
      }
      // 因为不支持 Map.values() 返回的结果，所以这里进行转换。
      let list = [];
      treeNodeMap.forEach(function (value, key, map) {
        list.push(value)
      });
      // console.log(list);

      yield put({
        type: 'treeSuccess',
        payload: {
          list: list,
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
