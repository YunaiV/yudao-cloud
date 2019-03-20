import {message} from 'antd';
import {buildTreeNode, findCheckedKeys} from '../../utils/tree.utils';
import {
  addAdmin,
  adminRoleAssign,
  deleteAdmin,
  queryAdmin,
  queryAdminRoleList,
  updateAdmin,
  updateAdminStatus,
} from '../../services/admin';
import {arrayToStringParams} from '../../utils/request.qs';
import PaginationHelper from '../../../helpers/PaginationHelper';

const SEARCH_PARAMS_DEFAULT = {
  nickname: '',
};

export default {
  namespace: 'adminList',

  state: {
    list: [],
    searchParams: SEARCH_PARAMS_DEFAULT,
    pagination: PaginationHelper.defaultPaginationConfig,

    roleList: [],
    roleCheckedKeys: [],
    roleAssignLoading: false,
  },

  effects: {
    // 查询列表
    * query({ payload }, { call, put }) {
      const response = yield call(queryAdmin, payload);
      yield put({
        type: 'querySuccess',
        payload: {
          list: response.data.list,
          pagination: PaginationHelper.formatPagination(response.data, payload),
          searchParams: {
            nickname: payload.nickname || ''
          }
        },
      });
    },
    *add({ payload }, { call, put }) {
      const { callback, body, queryParams } = payload;
      const response = yield call(addAdmin, body);
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
      const response = yield call(updateAdmin, body);
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
    *updateStatus({ payload }, { call, put }) {
      const { body, queryParams } = payload;
      yield call(updateAdminStatus, body);
      message.info('更新成功!');
      yield put({
        type: 'query',
        payload: {
          ...queryParams,
        },
      });
    },
    *delete({ payload }, { call, put }) {
      const { queryParams, body } = payload;
      yield call(deleteAdmin, body);
      message.info('删除成功!');
      yield put({
        type: 'query',
        payload: {
          ...queryParams,
        },
      });
    },

    *queryRoleList({ payload }, { call, put }) {
      yield put({
        type: 'changeRoleAssignLoading',
        payload: true,
      });

      const response = yield call(queryAdminRoleList, payload);
      const roleList = response.data;
      const roleTreeData = buildTreeNode(roleList, 'name', 'id');
      const roleCheckedKeys = findCheckedKeys(roleList);

      yield put({
        type: 'querySuccess',
        payload: {
          roleList: roleTreeData,
          roleCheckedKeys,
        },
      });

      yield put({
        type: 'changeRoleAssignLoading',
        payload: false,
      });
    },
    *roleAssign({ payload }, { call }) {
      const params = {
        id: payload.id,
        roleIds: arrayToStringParams(payload.roleIds),
      };
      const response = yield call(adminRoleAssign, params);
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
    changeRoleCheckedKeys(state, { payload }) {
      return {
        ...state,
        roleCheckedKeys: payload,
      };
    },
    changeRoleAssignLoading(state, { payload }) {
      return {
        ...state,
        roleAssignLoading: payload,
      };
    },
  },
};
