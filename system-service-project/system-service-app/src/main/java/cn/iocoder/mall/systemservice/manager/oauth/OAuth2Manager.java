package cn.iocoder.mall.systemservice.manager.oauth;

import cn.iocoder.mall.systemservice.convert.oauth.OAuth2Convert;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.vo.OAuth2AccessTokenVO;
import cn.iocoder.mall.systemservice.service.oauth.OAuth2Service;
import cn.iocoder.mall.systemservice.service.oauth.bo.OAuth2AccessTokenBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OAuth2.0 Manager
 */
@Service
public class OAuth2Manager {

    @Autowired
    private OAuth2Service oauth2Service;

    public OAuth2AccessTokenVO createAccessToken(OAuth2CreateAccessTokenDTO createAccessTokenDTO) {
        OAuth2AccessTokenBO accessTokenBO = oauth2Service.createAccessToken(createAccessTokenDTO.getUserId(),
                createAccessTokenDTO.getUserType(), createAccessTokenDTO.getCreateIp());
        return OAuth2Convert.INSTANCE.convert(accessTokenBO);
    }

    public OAuth2AccessTokenVO checkAccessToken(String accessToken) {
        OAuth2AccessTokenBO accessTokenBO = oauth2Service.checkAccessToken(accessToken);
        return OAuth2Convert.INSTANCE.convert(accessTokenBO);
    }

    public OAuth2AccessTokenVO refreshAccessToken(OAuth2RefreshAccessTokenDTO refreshAccessTokenDTO) {
        OAuth2AccessTokenBO accessTokenBO = oauth2Service.refreshAccessToken(refreshAccessTokenDTO.getRefreshToken(),
                refreshAccessTokenDTO.getCreateIp());
        return OAuth2Convert.INSTANCE.convert(accessTokenBO);
    }

}
