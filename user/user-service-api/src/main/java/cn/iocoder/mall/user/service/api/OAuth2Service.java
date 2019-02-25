package cn.iocoder.mall.user.service.api;


import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.service.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.user.service.api.bo.OAuth2AuthenticationBO;

public interface OAuth2Service {

    /**
     * 使用手机号 + 验证码，获取访问令牌等信息
     *
     * 如果手机未注册，并且验证码正确，进行自动注册。
     *
     * @param mobile 手机号
     * @param code   验证码
     * @return 授权信息
     */
    OAuth2AccessTokenBO getAccessToken(String mobile, String code)
            throws ServiceException;

    CommonResult<OAuth2AccessTokenBO> getAccessToken2(String mobile, String code);

    /**
     * 校验访问令牌，获取身份信息( 不包括 accessToken 等等 )
     *
     * @param accessToken 访问令牌
     * @return 授权信息
     */
    OAuth2AuthenticationBO checkToken(String accessToken)
            throws ServiceException;

    // @see 刷新 token

    // @see 移除 token

}