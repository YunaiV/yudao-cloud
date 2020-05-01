import { message } from 'antd';
import { buildTreeNode, findCheckedKeys } from '../../utils/tree.utils';
import {
  addAdmin,
  adminRoleAssign,
  deleteAdmin,
  queryAdminRoleList,
  updateAdmin,
  updateAdminStatus,
  deptTreeAll,
} from '../../services/admin';
import {
  adminPage
} from '../../services/system';
import { arrayToStringParams } from '../../utils/request.qs';
import PaginationHelper from '../../../helpers/PaginationHelper';

const SEARCH_PARAMS_DEFAULT = {
  name: '',
};

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
  namespace: 'adminList',

  state: {
    // 分页列表相关
    list: [],
    listLoading: false,
    pagination: PaginationHelper.defaultPaginationConfig,
    searchParams: SEARCH_PARAMS_DEFAULT,

    // 添加 or 修改表单相关
    modalVisible: false,
    modalType: undefined, // 'add' or 'update' 表单
    formVals: {}, // 当前表单值
    modalLoading: false,

    // 分配角色表单相关
    roleList: [],
    roleModalVisible: false,
    roleCheckedKeys: [], // 此处的 Key ，就是角色编号
    roleAssignLoading: false,

    //部门相关
    deptSelectTree: [],
  },

  effects: {
    *getDeptmentTree({ payload }, { call, put }) {
      const result = yield call(deptTreeAll, payload);
      yield put({
        type: 'treeSuccess',
        payload: result.data,
      });
    },

    // 查询列表
    *query({ payload }, { call, put }) {
      // 显示加载中
      yield put({
        type: 'changeListLoading',
        payload: true,
      });

      // 请求
      const response = yield call(adminPage, payload);
      // 响应
      yield put({
        type: 'setAll',
        payload: {
          list: response.data.list,
          pagination: PaginationHelper.formatPagination(response.data, payload),
          searchParams: {
            name: payload.name || '',
          },
        },
      });

      // 隐藏加载中
      yield put({
        type: 'changeListLoading',
        payload: false,
      });
    },
    *add({ payload }, { call, put }) {
      // 显示加载中
      yield put({
        type: 'changeModalLoading',
        payload: true,
      });

      // 请求
      const { callback, body } = payload;
      const response = yield call(addAdmin, body);
      // 响应
      if (response.code === 0) {
        if (callback) {
          callback(response);
        }
        // 刷新列表
        yield put({
          type: 'query',
          payload: {
            ...PaginationHelper.defaultPayload,
          },
        });
      }

      // 隐藏加载中
      yield put({
        type: 'changeModalLoading',
        payload: false,
      });
    },
    *update({ payload }, { call, put }) {
      const { callback, body } = payload;
      // 显示加载中
      yield put({
        type: 'changeModalLoading',
        payload: true,
      });

      // 请求
      const response = yield call(updateAdmin, body);
      // 响应
      if (response.code === 0) {
        if (callback) {
          callback(response);
        }
        // 刷新列表
        yield put({
          type: 'query',
          payload: {
            ...PaginationHelper.defaultPayload,
          },
        });
      }

      // 隐藏加载中
      yield put({
        type: 'changeModalLoading',
        payload: false,
      });
    },

    *updateStatus({ payload }, { call, put }) {
      // 请求
      const response = yield call(updateAdminStatus, payload);
      // 响应
      if (response.code === 0) {
        message.info('更新状态成功!');
        // 刷新列表
        yield put({
          type: 'query',
          payload: {
            ...PaginationHelper.defaultPayload,
          },
        });
      }
    },

    *delete({ payload }, { call, put }) {
      // 请求
      const response = yield call(deleteAdmin, payload);
      // 响应
      if (response.code === 0) {
        message.info('删除成功!');
        // 刷新列表
        yield put({
          type: 'query',
          payload: {
            ...PaginationHelper.defaultPayload,
          },
        });
      }
    },

    *queryRoleList({ payload }, { call, put }) {
      // 显示加载中
      yield put({
        type: 'changeRoleAssignLoading',
        payload: true,
      });

      // 请求
      const response = yield call(queryAdminRoleList, payload);
      // 响应
      if (response.code === 0) {
        const roleList = response.data;
        const roleTreeData = buildTreeNode(roleList, 'name', 'id');
        const roleCheckedKeys = findCheckedKeys(roleList);
        yield put({
          type: 'setAll',
          payload: {
            roleList: roleTreeData,
            roleCheckedKeys,
          },
        });
      }

      // 隐藏加载中
      yield put({
        type: 'changeRoleAssignLoading',
        payload: false,
      });
    },

    *roleAssign({ payload }, { call, put }) {
      const { callback, body } = payload;
      // 显示加载中
      yield put({
        type: 'changeRoleAssignLoading',
        payload: true,
      });

      // 请求
      const response = yield call(adminRoleAssign, {
        id: body.id,
        roleIds: arrayToStringParams(body.roleIds),
      });
      // 响应
      if (response.code === 0) {
        if (callback) {
          callback(response);
        }
      }

      // 隐藏加载中
      yield put({
        type: 'changeRoleAssignLoading',
        payload: false,
      });
    },
  },

  reducers: {
    treeSuccess(state, { payload }) {
      const resultData = payload;
      const treeData = buildSelectTree(resultData);

      // // value 要保护 displayName 不然，搜索会失效
      // const rootNode = [
      //   {
      //     title: '根节点',
      //     value: `根节点-0`,
      //     key: 0,
      //     children: [],
      //   },
      // ];

      // const deptSelectTree = rootNode.concat(treeData);
      return {
        ...state,
        // list: resultData,
        deptSelectTree: treeData,
      };
    },
    changeRoleCheckedKeys(state, { payload }) {
      return {
        ...state,
        roleCheckedKeys: payload,
      };
    },
    // 修改加载中的状态
    changeRoleAssignLoading(state, { payload }) {
      return {
        ...state,
        roleAssignLoading: payload,
      };
    },
    changeModalLoading(state, { payload }) {
      return {
        ...state,
        modalLoading: payload,
      };
    },
    changeListLoading(state, { payload }) {
      return {
        ...state,
        listLoading: payload,
      };
    },
    // 设置所有属性
    setAll(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    },
  },
};
