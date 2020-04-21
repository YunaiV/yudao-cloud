package cn.iocoder.mall.security.core.account;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.rpc.api.oauth2.OAuth2RPC;
import cn.iocoder.mall.system.rpc.request.oauth2.OAuth2AccessTokenAuthenticateRequest;
import cn.iocoder.mall.system.rpc.response.oauth2.OAuth2AccessTokenResponse;
import cn.iocoder.mall.web.core.util.CommonWebUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountAuthInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(validation = "true", version = "${dubbo.consumer.OAuth2RPC.version}")
    private OAuth2RPC oauth2RPC;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 执行认证
        String accessToken = HttpUtil.obtainAuthorization(request);
        OAuth2AccessTokenAuthenticateRequest oauth2AccessTokenAuthenticateRequest = new OAuth2AccessTokenAuthenticateRequest()
                .setAccessToken(accessToken).setIp(HttpUtil.getIp(request));
        CommonResult<OAuth2AccessTokenResponse> oauth2AccessTokenResponseResult = oauth2RPC.authenticate(oauth2AccessTokenAuthenticateRequest);
        if (oauth2AccessTokenResponseResult.isError()) { // TODO 有一个问题点，假设 token 认证失败，但是该 url 是无需认证的，是不是一样能够执行过去？
            throw ServiceExceptionUtil.exception(oauth2AccessTokenResponseResult);
        }
        // 设置账号编号
        CommonWebUtil.setAccountId(request, oauth2AccessTokenResponseResult.getData().getAccountId());
        return true;
    }

}
