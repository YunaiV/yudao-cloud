package cn.iocoder.mall.system.biz.service.oauth2;

import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AuthenticateBO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2AccessTokenAuthenticateDTO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2MobileCodeAuthenticateDTO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2RefreshTokenAuthenticateDTO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2UsernameAuthenticateDTO;

/**
 * OAuth2 Service 接口
 */
public interface OAuth2Service {

    OAuth2AuthenticateBO authenticate(OAuth2UsernameAuthenticateDTO authenticateDTO);

    OAuth2AuthenticateBO authenticate(OAuth2MobileCodeAuthenticateDTO authenticateDTO);

    OAuth2AuthenticateBO authenticate(OAuth2AccessTokenAuthenticateDTO authenticateDTO);

    OAuth2AuthenticateBO authenticate(OAuth2RefreshTokenAuthenticateDTO authenticateDTO);

}
