package cn.iocoder.mall.systemservice.rpc.oauth;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.manager.oauth.OAuth2Manager;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.vo.OAuth2AccessTokenVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Service(version = "${dubbo.provider.OAuth2Rpc.version}", validation = "false")
public class OAuth2RpcImpl implements OAuth2Rpc {

    @Autowired
    private OAuth2Manager oauth2Manager;

    @Override
    public CommonResult<OAuth2AccessTokenVO> createAccessToken(OAuth2CreateAccessTokenDTO createAccessTokenDTO) {
        return success(oauth2Manager.createAccessToken(createAccessTokenDTO));
    }

    @Override
    public CommonResult<OAuth2AccessTokenVO> checkAccessToken(String accessToken) {
        return success(oauth2Manager.checkAccessToken(accessToken));
    }

    @Override
    public CommonResult<OAuth2AccessTokenVO> refreshAccessToken(OAuth2RefreshAccessTokenDTO refreshAccessTokenDTO) {
        return success(oauth2Manager.refreshAccessToken(refreshAccessTokenDTO));
    }

}
