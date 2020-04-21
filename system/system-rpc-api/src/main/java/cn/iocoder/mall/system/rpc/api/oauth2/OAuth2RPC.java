package cn.iocoder.mall.system.rpc.api.oauth2;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.rpc.request.oauth2.OAuth2AccessTokenAuthenticateRequest;
import cn.iocoder.mall.system.rpc.response.oauth2.OAuth2AccessTokenResponse;

public interface OAuth2RPC {

    CommonResult<OAuth2AccessTokenResponse> authenticate(OAuth2AccessTokenAuthenticateRequest request);

}
