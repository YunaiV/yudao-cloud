import adminMenu from './geographic/admin-menu.json';
import adminMenuAll from './geographic/admin-menu-all.json';
import adminUrls from './geographic/admin-urls';

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

export default {
  'GET /admin-api/admin/resource/admin_menu_tree': getAdminMenuAll,
  'GET /admin-api/admin/resource/admin_url_list': getAdminUrls,
};
