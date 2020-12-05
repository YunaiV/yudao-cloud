package cn.iocoder.mall.systemservice.rpc.oauth;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RemoveTokenByUserReqDTO;
import cn.iocoder.mall.systemservice.service.oauth.OAuth2Service;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@DubboService
public class OAuth2RpcImpl implements OAuth2Rpc {

    @Autowired
    private OAuth2Service oAuth2Service;

    @Override
    public CommonResult<OAuth2AccessTokenRespDTO> createAccessToken(OAuth2CreateAccessTokenReqDTO createAccessTokenDTO) {
        return success(oAuth2Service.createAccessToken(createAccessTokenDTO));
    }

    @Override
    public CommonResult<OAuth2AccessTokenRespDTO> checkAccessToken(String accessToken) {
        return success(oAuth2Service.checkAccessToken(accessToken));
    }

    @Override
    public CommonResult<OAuth2AccessTokenRespDTO> refreshAccessToken(OAuth2RefreshAccessTokenReqDTO refreshAccessTokenDTO) {
        return success(oAuth2Service.refreshAccessToken(refreshAccessTokenDTO));
    }

    @Override
    public CommonResult<Boolean> removeToken(OAuth2RemoveTokenByUserReqDTO removeTokenDTO) {
        oAuth2Service.removeToken(removeTokenDTO);
        return success(true);
    }

}
