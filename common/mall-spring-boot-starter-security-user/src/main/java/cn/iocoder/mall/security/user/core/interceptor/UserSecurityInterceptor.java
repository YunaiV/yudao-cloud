package cn.iocoder.mall.security.user.core.interceptor;

import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.user.core.context.UserSecurityContext;
import cn.iocoder.mall.security.user.core.context.UserSecurityContextHolder;
import cn.iocoder.mall.systemservice.rpc.oauth.OAuth2Rpc;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.web.core.util.CommonWebUtil;
import cn.iocoder.security.annotations.RequiresAuthenticate;
import cn.iocoder.security.annotations.RequiresPermissions;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.iocoder.common.framework.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;
import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants.OAUTH_USER_TYPE_ERROR;

public class UserSecurityInterceptor extends HandlerInterceptorAdapter {

    @Reference(version = "${dubbo.consumer.OAuth2Rpc.version}")
    private OAuth2Rpc oauth2Rpc;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获得访问令牌
        Integer userId = this.obtainUserId(request);
        // 校验认证
        this.checkAuthentication((HandlerMethod) handler, userId);
        return true;
    }

    private Integer obtainUserId(HttpServletRequest request) {
        String accessToken = HttpUtil.obtainAuthorization(request);
        Integer userId = null;
        if (accessToken != null) {
            CommonResult<OAuth2AccessTokenRespDTO> checkAccessTokenResult = oauth2Rpc.checkAccessToken(accessToken);
            checkAccessTokenResult.checkError();
            // 校验用户类型正确
            if (!UserTypeEnum.USER.getValue().equals(checkAccessTokenResult.getData().getUserType())) {
                throw ServiceExceptionUtil.exception(OAUTH_USER_TYPE_ERROR);
            }
            // 获得用户编号
            userId = checkAccessTokenResult.getData().getUserId();
            // 设置到 Request 中
            CommonWebUtil.setUserId(request, userId);
            CommonWebUtil.setUserType(request, UserTypeEnum.USER.getValue());
            // 设置到
            UserSecurityContext userSecurityContext = new UserSecurityContext().setUserId(userId);
            UserSecurityContextHolder.setContext(userSecurityContext);
        }
        return userId;
    }

    private void checkAuthentication(HandlerMethod handlerMethod, Integer userId) {
        boolean requiresAuthenticate = false; // 对于 USER 来说，默认无需登录
        if (handlerMethod.hasMethodAnnotation(RequiresAuthenticate.class)
                || handlerMethod.hasMethodAnnotation(RequiresPermissions.class)) { // 如果需要权限验证，也认为需要认证
            requiresAuthenticate = true;
        }
        if (requiresAuthenticate && userId == null) {
            throw ServiceExceptionUtil.exception(UNAUTHORIZED);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清空 SecurityContext
        UserSecurityContextHolder.clear();
    }

}
