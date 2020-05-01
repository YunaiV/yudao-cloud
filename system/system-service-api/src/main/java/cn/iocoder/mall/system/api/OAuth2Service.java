package cn.iocoder.mall.system.api;

import cn.iocoder.mall.system.api.dto.oauth2.OAuth2RemoveTokenByUserDTO;

/**
 * Oauth2 服务接口
 */
public interface OAuth2Service {

    /**
     * 基于用户移除 accessToken
     *
     * @param oauth2RemoveTokenDTO accessToken 信息
     */
    void removeToken(OAuth2RemoveTokenByUserDTO oauth2RemoveTokenDTO);

}
