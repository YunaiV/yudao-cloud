import { message } from 'antd';
import { arrayToStringParams } from '../../utils/request.qs';
import {
  addRole,
  updateRole,
  deleteRole,
  queryRole,
  queryRoleResourceTree,
  roleAssignResource,
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

function findNodes(id, nodes) {
  const res = [];
  for (let i = 0; i < nodes.length; i += 1) {
    const node = nodes[i];
    if (node.key === id) {
      res.push(node.key);
      break;
    } else {
      const childNodes = findNodes(id, node.children);
      if (childNodes.length) {
        res.push(node.key);
        for (let j = 0; j < childNodes.length; j += 1) {
          res.push(childNodes[j]);
        }
        break;
      }
    }
  }
  return res;
}

function findAllNodes(resourceIds, nodes) {
  const findNodesArray = [];
  for (let i = 0; i < resourceIds.length; i += 1) {
    const findNodesData = findNodes(resourceIds[i], nodes);
    if (findNodesData) {
      for (let j = 0; j < findNodesData.length; j += 1) {
        const jD = findNodesData[j];
        if (findNodesArray.indexOf(jD) === -1) {
          findNodesArray.push(jD);
        }
      }
    }
  }
  return findNodesArray;
}

function findCheckedKeys(nodes) {
  let res = [];
  for (let i = 0; i < nodes.length; i += 1) {
    const node = nodes[i];
    if (node.children) {
      const findChildrenNodes = findCheckedKeys(node.children);
      if (findChildrenNodes) {
        res = res.concat(findChildrenNodes);
      }
    } else if (node.assigned === true) {
      res.push(node.id);
    }
  }
  return res;
}

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
