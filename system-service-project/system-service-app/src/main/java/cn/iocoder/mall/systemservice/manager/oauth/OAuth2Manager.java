package cn.iocoder.mall.systemservice.manager.oauth;

import cn.iocoder.mall.systemservice.convert.oauth.OAuth2Convert;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RemoveTokenByUserReqDTO;
import cn.iocoder.mall.systemservice.service.oauth.OAuth2Service;
import cn.iocoder.mall.systemservice.service.oauth.bo.OAuth2AccessTokenBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * OAuth2.0 Manager
 */
@Service
@Validated
public class OAuth2Manager {

    @Autowired
    private OAuth2Service oauth2Service;

    public OAuth2AccessTokenRespDTO createAccessToken(@Valid OAuth2CreateAccessTokenReqDTO createAccessTokenDTO) {
        OAuth2AccessTokenBO accessTokenBO = oauth2Service.createAccessToken(createAccessTokenDTO.getUserId(),
                createAccessTokenDTO.getUserType(), createAccessTokenDTO.getCreateIp());
        return OAuth2Convert.INSTANCE.convert(accessTokenBO);
    }

    public OAuth2AccessTokenRespDTO checkAccessToken(String accessToken) {
        OAuth2AccessTokenBO accessTokenBO = oauth2Service.checkAccessToken(accessToken);
        return OAuth2Convert.INSTANCE.convert(accessTokenBO);
    }

    public OAuth2AccessTokenRespDTO refreshAccessToken(@Valid OAuth2RefreshAccessTokenReqDTO refreshAccessTokenDTO) {
        OAuth2AccessTokenBO accessTokenBO = oauth2Service.refreshAccessToken(refreshAccessTokenDTO.getRefreshToken(),
                refreshAccessTokenDTO.getCreateIp());
        return OAuth2Convert.INSTANCE.convert(accessTokenBO);
    }

    public void removeToken(@Valid OAuth2RemoveTokenByUserReqDTO removeTokenDTO) {
        oauth2Service.removeToken(removeTokenDTO.getUserId(), removeTokenDTO.getUserType());
    }

}
