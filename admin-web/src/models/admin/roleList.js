import { message } from 'antd';
import { arrayToStringParams } from '../../utils/request.qs';
import { buildTreeNode, findAllNodes, findCheckedKeys } from '../../utils/tree.utils';
import {
  authorizationRoleResourceTree,
  authorizationRoleAssignResource,
} from '../../services/system';
import {
  rolePage,
  roleCreate,
  roleUpdate,
  roleDelete,
} from '../../services/system';


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
      const response = yield call(roleCreate, body);
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
      const response = yield call(roleUpdate, body);
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
      yield call(roleDelete, body);
      message.info('删除成功!');
      yield put({
        type: 'query',
        payload: {
          ...queryParams,
        },
      });
    },
    *query({ payload }, { call, put }) {
      const response = yield call(rolePage, payload);
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

      const response = yield call(authorizationRoleResourceTree, payload);
      const roleResourceTree = response.data;
      const roleTreeData = buildTreeNode(roleResourceTree, 'name', 'id');
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
      const { roleId, resourceIds, roleTreeData } = payload;
      const assignNodes = findAllNodes(resourceIds, roleTreeData);
      const params = {
        roleId,
        resourceIds: arrayToStringParams(assignNodes),
      };
      const response = yield call(authorizationRoleAssignResource, params);
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
