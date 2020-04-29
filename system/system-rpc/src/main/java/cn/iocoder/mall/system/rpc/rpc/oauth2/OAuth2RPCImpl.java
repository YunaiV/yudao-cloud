package cn.iocoder.mall.system.rpc.rpc.oauth2;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AuthenticateBO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2AccessTokenAuthenticateDTO;
import cn.iocoder.mall.system.biz.service.oauth2.OAuth2Service;
import cn.iocoder.mall.system.rpc.api.oauth2.OAuth2RPC;
import cn.iocoder.mall.system.rpc.convert.oauth2.OAuth2Convert;
import cn.iocoder.mall.system.rpc.request.oauth2.OAuth2AccessTokenAuthenticateRequest;
import cn.iocoder.mall.system.rpc.response.oauth2.OAuth2AccessTokenResponse;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "${dubbo.provider.OAuth2RPC.version}", validation = "true")
public class OAuth2RPCImpl implements OAuth2RPC {

    @Autowired
    private OAuth2Service oauth2Service;

    @Override
    public CommonResult<OAuth2AccessTokenResponse> authenticate(OAuth2AccessTokenAuthenticateRequest authenticateRequest) {
        // 执行认证
        OAuth2AccessTokenAuthenticateDTO authenticateDTO = OAuth2Convert.INSTANCE.convert(authenticateRequest);
        OAuth2AuthenticateBO accessTokenBO = oauth2Service.authenticate(authenticateDTO);
        // 返回结果
        OAuth2AccessTokenResponse accessTokenResponse = OAuth2Convert.INSTANCE.convert(accessTokenBO);
        return CommonResult.success(accessTokenResponse);
    }

}
