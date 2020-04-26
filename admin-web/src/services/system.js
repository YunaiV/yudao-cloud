import { stringify } from '@/utils/request.qs';
import request from '@/utils/request';


export async function usernameAuthenticate(params) {
  return request(`/system-api/admins/oauth2/username-authenticate?${stringify(params)}`, {
    method: 'POST',
    body: {},
  });
}
