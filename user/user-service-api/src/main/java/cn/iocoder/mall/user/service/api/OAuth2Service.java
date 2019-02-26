package cn.iocoder.mall.user.service.api;


import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.service.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.user.service.api.bo.OAuth2AuthenticationBO;

public interface OAuth2Service {

    CommonResult<OAuth2AccessTokenBO> getAccessToken(String mobile, String code);

    /**
     * 校验访问令牌，获取身份信息( 不包括 accessToken 等等 )
     *
     * @param accessToken 访问令牌
     * @return 授权信息
     */
    CommonResult<OAuth2AuthenticationBO> checkToken(String accessToken);

    // TODO @see 刷新 token

    // TODO @see 移除 token

}