package cn.iocoder.mall.system.api;

import cn.iocoder.mall.system.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.system.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2CreateTokenDTO;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2GetTokenDTO;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2RefreshTokenDTO;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2RemoveTokenByUserDTO;

/**
 * Oauth2 服务接口
 */
public interface OAuth2Service {

    /**
     * 根据身份信息，创建 accessToken 信息
     *
     * @param oauth2CreateTokenDTO 身份信息 DTO
     * @return accessToken 信息
     */
    OAuth2AccessTokenBO createToken(OAuth2CreateTokenDTO oauth2CreateTokenDTO);

    /**
     * 基于用户移除 accessToken
     *
     * @param oauth2RemoveTokenDTO accessToken 信息
     */
    void removeToken(OAuth2RemoveTokenByUserDTO oauth2RemoveTokenDTO);

    /**
     * 刷新令牌，获得新的 accessToken 信息
     *
     * @param oauth2RefreshTokenDTO refreshToken 信息
     * @return accessToken 信息
     */
    OAuth2AccessTokenBO refreshToken(OAuth2RefreshTokenDTO oauth2RefreshTokenDTO);

    /**
     * 通过 accessToken 获得身份信息
     *
     * @param oauth2GetTokenDTO accessToken 信息
     * @return 身份信息
     */
    OAuth2AuthenticationBO getAuthentication(OAuth2GetTokenDTO oauth2GetTokenDTO);

}
