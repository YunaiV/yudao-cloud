import adminMenu from './geographic/admin-menu.json';
import adminMenuAll from './geographic/admin-menu-all.json';
import adminUrls from './geographic/admin-urls';
import resourceTree from './geographic/resource-tree.json';
import roleQuery from './geographic/role-query.json';

/* eslint-disable */
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

export default {
  'GET /admin-api/admin/resource/admin_menu_tree': getAdminMenuAll,
  'GET /admin-api/admin/resource/admin_url_list': getAdminUrls,
  'GET /admin-api/admin/resource/tree': getResourceTree,
  'GET /admin-api/admin/role/page': getQueryRole,
};
