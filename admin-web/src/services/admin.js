import { stringify } from 'qs';
import request from '@/utils/request';

export async function getAdminMenus() {
  return request('/admin-api/admin/resource/admin_menu_tree');
}

export async function getAdminUrls(params) {
  return request(`/admin-api/admin/resource/admin_url_list?${stringify(params)}`);
}
