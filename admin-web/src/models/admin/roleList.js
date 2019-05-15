import { message } from 'antd';
import { arrayToStringParams } from '../../utils/request.qs';
import { buildTreeNode, findAllNodes, findCheckedKeys } from '../../utils/tree.utils';
import {
  addRole,
  updateRole,
  deleteRole,
  queryRole,
  queryRoleResourceTree,
  roleAssignResource,
} from '../../services/admin';

export default {
  namespace: 'roleList',

  state: {
    list: [],
    count: 0,
    pageNo: 0,
    pageSize: 10,

    roleTreeData: [],
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
      const { total, list } = response.data;
      yield put({
        type: 'querySuccess',
        payload: {
          list: list,
          count: total,
        },
      });
    },
    *queryRoleAssign({ payload }, { call, put }) {
      yield put({
        type: 'changeAssignModalLoading',
        payload: true,
      });

      const response = yield call(queryRoleResourceTree, payload);
      const roleResourceTree = response.data;
      const roleTreeData = buildTreeNode(roleResourceTree, 'displayName', 'id');
      const checkedKeys = findCheckedKeys(roleResourceTree);

      yield put({
        type: 'querySuccess',
        payload: {
          checkedKeys,
          roleTreeData,
        },
      });

      yield put({
        type: 'changeAssignModalLoading',
        payload: false,
      });
    },
    *roleAssignResource({ payload }, { call }) {
      const { id, resourceIds, roleTreeData } = payload;
      const assignNodes = findAllNodes(resourceIds, roleTreeData);
      const params = {
        id,
        resourceIds: arrayToStringParams(assignNodes),
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
