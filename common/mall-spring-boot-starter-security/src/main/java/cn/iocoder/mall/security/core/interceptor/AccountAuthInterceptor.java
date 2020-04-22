package cn.iocoder.mall.security.core.interceptor;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.core.annotation.RequiresAuthenticate;
import cn.iocoder.mall.security.core.annotation.RequiresNone;
import cn.iocoder.mall.security.core.annotation.RequiresPermissions;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.system.rpc.api.oauth2.OAuth2RPC;
import cn.iocoder.mall.system.rpc.request.oauth2.OAuth2AccessTokenAuthenticateRequest;
import cn.iocoder.mall.system.rpc.response.oauth2.OAuth2AccessTokenResponse;
import cn.iocoder.mall.web.core.util.CommonWebUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountAuthInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(validation = "true", version = "${dubbo.consumer.OAuth2RPC.version}")
    private OAuth2RPC oauth2RPC;


    /**
     * 是否默认要求认证
     *
     * 针对 /users/** 接口，一般默认不要求认证，因为面向用户的接口，往往不需要登陆即可访问
     * 针对 /admins/** 接口，一般默认要求认证，因为面向管理员的接口，往往是内部需要更严格的安全控制
     */
    private final boolean defaultRequiresAuthenticate;

    public AccountAuthInterceptor(boolean defaultRequiresAuthenticate) {
        this.defaultRequiresAuthenticate = defaultRequiresAuthenticate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 进行认证
        Integer accountId = this.obtainAccount(request);
        // 2. 进行鉴权
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 判断是否需要认证
        this.checkAuthenticate(handlerMethod, accountId);
        // 判断是否需要权限

        return true;
    }

    private Integer obtainAccount(HttpServletRequest request) {
        String accessToken = HttpUtil.obtainAuthorization(request); // 获得访问令牌
        if (!StringUtils.hasText(accessToken)) { // 如果未传递，则不进行认证
            return null;
        }
        // 执行认证
        OAuth2AccessTokenAuthenticateRequest oauth2AccessTokenAuthenticateRequest = new OAuth2AccessTokenAuthenticateRequest()
                .setAccessToken(accessToken).setIp(HttpUtil.getIp(request));
        CommonResult<OAuth2AccessTokenResponse> oauth2AccessTokenResponseResult = oauth2RPC.authenticate(oauth2AccessTokenAuthenticateRequest);
        if (oauth2AccessTokenResponseResult.isError()) { // TODO 有一个问题点，假设 token 认证失败，但是该 url 是无需认证的，是不是一样能够执行过去？
            throw ServiceExceptionUtil.exception(oauth2AccessTokenResponseResult);
        }
        // 设置账号编号
        Integer accountId = oauth2AccessTokenResponseResult.getData().getAccountId();
        CommonWebUtil.setAccountId(request, accountId);
        return accountId;
    }

    private void checkAuthenticate(HandlerMethod handlerMethod, Integer accountId) {
        boolean requiresAuthenticate = defaultRequiresAuthenticate;
        if (handlerMethod.hasMethodAnnotation(RequiresAuthenticate.class)
                || handlerMethod.hasMethodAnnotation(RequiresPermissions.class)) { // 如果需要权限验证，也认为需要认证
            requiresAuthenticate = true;
        } else if (handlerMethod.hasMethodAnnotation(RequiresNone.class)) {
            requiresAuthenticate = false;
        }
        if (requiresAuthenticate && accountId == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.OAUTH2_NOT_AUTHENTICATE);
        }
    }

    private void checkPermission(HandlerMethod handlerMethod, Integer accountId) {
        RequiresPermissions requiresPermissions = handlerMethod.getMethodAnnotation(RequiresPermissions.class);
        if (requiresPermissions == null) {
            return;
        }
        String[] permissions = requiresPermissions.value();
        if (CollectionUtil.isEmpty(permissions)) {
            return;
        }
        // 权限验证

    }

}
