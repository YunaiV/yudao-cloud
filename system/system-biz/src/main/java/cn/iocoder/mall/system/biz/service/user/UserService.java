package cn.iocoder.mall.system.biz.service.user;

import cn.iocoder.mall.system.biz.bo.user.UserAuthenticateBO;
import cn.iocoder.mall.system.biz.bo.user.UserBO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2MobileCodeAuthenticateDTO;

/**
 * 用户 Service 接口
 */
public interface UserService {

    UserAuthenticateBO authenticate(OAuth2MobileCodeAuthenticateDTO authenticateDTO);

    UserBO getUserByAccountId(Integer accountId);

}
