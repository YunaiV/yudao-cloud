import { message } from 'antd';
import { arrayToStringParams } from '../../utils/request.qs';
import {
  addRole,
  updateRole,
  deleteRole,
  queryRole,
  queryRoleResourceTree,
  roleAssignResource,
  resourceTree,
} from '../../services/admin';

function buildTreeNode(nodes, titleKey, nodeKey) {
  return nodes.map(item => {
    const res = {};
    if (item.children) {
      res.children = buildTreeNode(item.children, titleKey, nodeKey);
    }
    res.title = `${item.id}-${item[titleKey]}`;
    res.key = item[nodeKey];
    return res;
  });
}

function getKeys(treeData) {
  return treeData.map(item => {
    return item.key;
  });
}

export default {
  namespace: 'roleList',

  state: {
    list: [],
    count: 0,
    pageNo: 0,
    pageSize: 10,

    roleTreeData: [],
    resourceTreeData: [],
    checkedKeys: [],
    assignModalLoading: true,
  },

  effects: {
    *add({ payload }, { call, put }) {
      const { callback, body, queryParams } = payload;
      const response = yield call(addRole, body);
      if (callback) {
        callback(response);
      }
      yield put({
        type: 'query',
        payload: {
          ...queryParams,
        },
      });
    },
    *update({ payload }, { call, put }) {
      const { callback, body, queryParams } = payload;
      const response = yield call(updateRole, body);
      if (callback) {
        callback(response);
      }
      yield put({
        type: 'query',
        payload: {
          ...queryParams,
        },
      });
    },
    *delete({ payload }, { call, put }) {
      const { queryParams, body } = payload;
      yield call(deleteRole, body);
      message.info('删除成功!');
      yield put({
        type: 'query',
        payload: {
          ...queryParams,
        },
      });
    },
    *query({ payload }, { call, put }) {
      const response = yield call(queryRole, payload);
      message.info('查询成功!');
      const { count, roles } = response.data;
      yield put({
        type: 'querySuccess',
        payload: {
          list: roles,
          count,
        },
      });
    },
    *queryRoleAssign({ payload }, { call, put }) {
      yield put({
        type: 'changeAssignModalLoading',
        payload: true,
      });

      const roleResourceResponse = yield call(queryRoleResourceTree, payload);
      const resourceTreeResponse = yield call(resourceTree);

      const roleTreeData = buildTreeNode(roleResourceResponse.data, 'displayName', 'id');
      const resourceTreeData = buildTreeNode(resourceTreeResponse.data, 'displayName', 'id');
      const roleTreeIdKeys = getKeys(roleTreeData);
      const resourceTreeIdKeys = getKeys(resourceTreeData);

      const checkedKeys = roleTreeIdKeys.filter(roleKey => {
        let res = false;
        resourceTreeIdKeys.map(key => {
          if (key === roleKey) {
            res = true;
          }
          return key;
        });
        return res;
      });

      yield put({
        type: 'querySuccess',
        payload: {
          checkedKeys,
          roleTreeData,
          resourceTreeData,
        },
      });

      yield put({
        type: 'changeAssignModalLoading',
        payload: false,
      });
    },
    *roleAssignResource({ payload }, { call }) {
      const { id, resourceIds } = payload;
      const params = {
        id,
        resourceIds: arrayToStringParams(resourceIds),
      };
      const response = yield call(roleAssignResource, params);
      if (response.code === 0) {
        message.info('操作成功!');
      }
    },
  },

  reducers: {
    querySuccess(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    },
    changeCheckedKeys(state, { payload }) {
      return {
        ...state,
        checkedKeys: payload,
      };
    },
    changeAssignModalLoading(state, { payload }) {
      return {
        ...state,
        assignModalLoading: payload,
      };
    },
  },
};
