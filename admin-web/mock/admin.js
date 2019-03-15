/* eslint-disable */
import mockjs from 'mockjs';
import { resultBody } from './mock-comment';
import adminMenu from './geographic/admin-menu.json';
import adminMenuAll from './geographic/admin-menu-all.json';
import adminUrls from './geographic/admin-urls';
import resourceTree from './geographic/resource-tree.json';
import roleQuery from './geographic/role-query.json';
import dictionaryList from './geographic/dictionary-list.json';

function getAdminMenu(req, res) {
  return res.json(adminMenu);
}

function getAdminMenuAll(req, res) {
  return res.json(adminMenuAll);
}

function getAdminUrls(req, res) {
  return res.json(adminUrls);
}

function getResourceTree(req, res) {
  return res.json(resourceTree);
}

function getQueryRole(req, res) {
  return res.json(roleQuery);
}

function getDictionaryKeys(req, res) {
  const values = mockjs.mock({
    'list|5': [{ 'value|+1': 0, text: '@city' }],
  });

  return res.json(resultBody(values));
}

function getDictionaryText(req, res) {
  const values = mockjs.mock({
    text: '@city',
  });

  return res.json(resultBody(values));
}

function getDictionaryList(req, res) {
    return res.json(dictionaryList);
}

export default {
  'GET /admin-api/admins/admin/menu_resource_tree': getAdminMenu,
  'GET /admin-api/admins/admin/url_resource_list': getAdminUrls,
  'GET /admin-api/admins/resource/tree': getResourceTree,
  'GET /admin-api/admins/role/page': getQueryRole,
  'GET /admin-api/admins/admin/page': getQueryRole,
  'GET /admin-api/admins/dictionary/getList': getDictionaryKeys,
  'GET /admin-api/admins/dictionary/queryText': getDictionaryText,
  // 'GET /admin-api/admins/data_dict/list': getDictionaryList,
};
