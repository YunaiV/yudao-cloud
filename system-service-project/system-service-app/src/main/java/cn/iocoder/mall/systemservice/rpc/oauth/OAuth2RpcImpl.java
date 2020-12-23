package cn.iocoder.mall.systemservice.rpc.oauth;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.manager.oauth.OAuth2Manager;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RemoveTokenByUserReqDTO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Service(version = "${dubbo.provider.OAuth2Rpc.version}")
public class OAuth2RpcImpl implements OAuth2Rpc {

    @Autowired
    private OAuth2Manager oauth2Manager;

    @Override
    public CommonResult<OAuth2AccessTokenRespDTO> createAccessToken(OAuth2CreateAccessTokenReqDTO createAccessTokenDTO) {
        return success(oauth2Manager.createAccessToken(createAccessTokenDTO));
    }

    @Override
    public CommonResult<OAuth2AccessTokenRespDTO> checkAccessToken(String accessToken) {
        return success(oauth2Manager.checkAccessToken(accessToken));
    }

    @Override
    public CommonResult<OAuth2AccessTokenRespDTO> refreshAccessToken(OAuth2RefreshAccessTokenReqDTO refreshAccessTokenDTO) {
        return success(oauth2Manager.refreshAccessToken(refreshAccessTokenDTO));
    }

    @Override
    public CommonResult<Boolean> removeToken(OAuth2RemoveTokenByUserReqDTO removeTokenDTO) {
        oauth2Manager.removeToken(removeTokenDTO);
        return success(true);
    }

}
