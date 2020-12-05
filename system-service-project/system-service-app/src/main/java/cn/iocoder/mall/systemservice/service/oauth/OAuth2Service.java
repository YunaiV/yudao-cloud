package cn.iocoder.mall.systemservice.service.oauth;

import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RemoveTokenByUserReqDTO;

/**
 * OAuth2.0 Service 接口
 */
public interface OAuth2Service {

    OAuth2AccessTokenRespDTO createAccessToken(OAuth2CreateAccessTokenReqDTO createAccessTokenDTO);

    OAuth2AccessTokenRespDTO checkAccessToken(String accessToken);

    OAuth2AccessTokenRespDTO refreshAccessToken(OAuth2RefreshAccessTokenReqDTO refreshAccessTokenDTO);

    void removeToken(OAuth2RemoveTokenByUserReqDTO removeTokenDTO);

}
