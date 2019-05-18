package cn.iocoder.mall.admin.sdk.interceptor;

import cn.iocoder.common.framework.constant.UserTypeEnum;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.util.MallUtil;
import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.mall.admin.api.AdminService;
import cn.iocoder.mall.admin.api.OAuth2Service;
import cn.iocoder.mall.admin.api.bo.admin.AdminAuthorizationBO;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2GetTokenDTO;
import cn.iocoder.mall.admin.sdk.annotation.RequiresPermissions;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContext;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Set;

/**
 * Admin 安全拦截器
 */
@Component
public class AdminSecurityInterceptor extends HandlerInterceptorAdapter {

    @Reference(validation = "true", version = "${dubbo.consumer.OAuth2Service.version:1.0.0}")
    private OAuth2Service oauth2Service;
    @Reference(validation = "true", version = "${dubbo.consumer.AdminService.version:1.0.0}")
    private AdminService adminService;

    /**
     * 忽略的 URL 集合，即无需经过认证
     *
     * 对于 Admin 的系统，默认所有接口都需要进行认证
     */
    @Value("${admins.security.ignore_urls:#{null}}")
    private Set<String> ignoreUrls;

    public AdminSecurityInterceptor setIgnoreUrls(Set<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
        return this;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 设置当前访问的用户类型。注意，即使未登陆，我们也认为是管理员
        MallUtil.setUserType(request, UserTypeEnum.ADMIN.getValue());

        // 根据 accessToken 获得认证信息，判断是谁
        String accessToken = HttpUtil.obtainAuthorization(request);
        OAuth2AuthenticationBO authentication = null;
        ServiceException serviceException = null;
        if (StringUtil.hasText(accessToken)) {
            try {
                authentication = oauth2Service.getAuthentication(new OAuth2GetTokenDTO().setAccessToken(accessToken)
                        .setUserType(UserTypeEnum.ADMIN.getValue()));
            } catch (ServiceException e) {
                serviceException = e;
            }
        }

        // 进行鉴权
        String url = request.getRequestURI();
        boolean needAuthentication = ignoreUrls == null || !ignoreUrls.contains(url);
        AdminAuthorizationBO authorization = null;
        if (needAuthentication) {
            if (serviceException != null) { // 认证失败，抛出上面认证失败的 ServiceException 异常
                throw serviceException;
            }
            if (authentication == null) { // 无认证信息，抛出未登陆 ServiceException 异常
                throw new ServiceException(AdminErrorCodeEnum.OAUTH2_NOT_LOGIN.getCode(), AdminErrorCodeEnum.OAUTH2_NOT_LOGIN.getMessage());
            }
            authorization = checkPermission(handler, authentication);
        }

        // 鉴权完成，初始化 AdminSecurityContext 上下文
        AdminSecurityContext context = new AdminSecurityContext();
        AdminSecurityContextHolder.setContext(context);
        if (authentication != null) {
            context.setAdminId(authentication.getUserId());
            MallUtil.setUserId(request, authentication.getUserId()); // 记录到 request 中，避免 AdminSecurityContext 后续清理掉后，其它地方需要用到 userId
            if (authorization != null) {
                context.setUsername(authorization.getUsername());
                context.setRoleIds(authorization.getRoleIds());
            }
        }

        // 返回成功
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清空 SecurityContext
        AdminSecurityContextHolder.clear();
    }

    private AdminAuthorizationBO checkPermission(Object handler, OAuth2AuthenticationBO authentication) {
        // 获得 @RequiresPermissions 注解
        Assert.isTrue(handler instanceof HandlerMethod, "handler 必须是 HandlerMethod 类型");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequiresPermissions requiresPermissions = handlerMethod.getMethodAnnotation(RequiresPermissions.class);
        // 执行校验
        return adminService.checkPermissions(authentication.getUserId(),
                requiresPermissions != null ? Arrays.asList(requiresPermissions.value()) : null);
    }

}
