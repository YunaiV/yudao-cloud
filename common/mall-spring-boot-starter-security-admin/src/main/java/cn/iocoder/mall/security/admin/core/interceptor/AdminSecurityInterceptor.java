package cn.iocoder.mall.security.admin.core.interceptor;

import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.common.framework.exception.GlobalException;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.admin.core.context.AdminSecurityContext;
import cn.iocoder.mall.security.admin.core.context.AdminSecurityContextHolder;
import cn.iocoder.mall.systemservice.rpc.oauth.OAuth2Rpc;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.rpc.permission.PermissionRpc;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionCheckDTO;
import cn.iocoder.mall.web.core.util.CommonWebUtil;
import cn.iocoder.security.annotations.RequiresNone;
import cn.iocoder.security.annotations.RequiresPermissions;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import static cn.iocoder.common.framework.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;
import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants.OAUTH_USER_TYPE_ERROR;

public class AdminSecurityInterceptor extends HandlerInterceptorAdapter {

    @Reference(version = "${dubbo.consumer.OAuth2Rpc.version}")
    private OAuth2Rpc oauth2Rpc;
    @Reference(version = "${dubbo.consumer.PermissionRpc.version}")
    private PermissionRpc permissionRpc;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获得访问令牌
        Integer adminId = this.obtainAdminId(request);
        // 校验认证
        this.checkAuthentication((HandlerMethod) handler, adminId);
        // 校验权限
        this.checkPermission((HandlerMethod) handler, adminId);
        return true;
    }

    private Integer obtainAdminId(HttpServletRequest request) {
        String accessToken = HttpUtil.obtainAuthorization(request);
        Integer adminId = null;
        if (accessToken != null) {
            CommonResult<OAuth2AccessTokenRespDTO> checkAccessTokenResult = oauth2Rpc.checkAccessToken(accessToken);
            checkAccessTokenResult.checkError();
            // 校验用户类型正确
            if (!UserTypeEnum.ADMIN.getValue().equals(checkAccessTokenResult.getData().getUserType())) {
                throw ServiceExceptionUtil.exception(OAUTH_USER_TYPE_ERROR);
            }
            // 获得用户编号
            adminId = checkAccessTokenResult.getData().getUserId();
            // 设置到 Request 中
            CommonWebUtil.setUserId(request, adminId);
            CommonWebUtil.setUserType(request, UserTypeEnum.ADMIN.getValue());
            // 设置到
            AdminSecurityContext adminSecurityContext = new AdminSecurityContext().setAdminId(adminId);
            AdminSecurityContextHolder.setContext(adminSecurityContext);
        }
        return adminId;
    }

    private void checkAuthentication(HandlerMethod handlerMethod, Integer adminId) {
        boolean requiresAuthenticate = !handlerMethod.hasMethodAnnotation(RequiresNone.class); // 对于 ADMIN 来说，默认需登录
        if (requiresAuthenticate && adminId == null) {
            throw new GlobalException(UNAUTHORIZED);
        }
    }

    private void checkPermission(HandlerMethod handlerMethod, Integer adminId) {
        RequiresPermissions requiresPermissions = handlerMethod.getMethodAnnotation(RequiresPermissions.class);
        if (requiresPermissions == null) {
            return;
        }
        String[] permissions = requiresPermissions.value();
        if (CollectionUtils.isEmpty(permissions)) {
            return;
        }
        // 权限验证
        permissionRpc.checkPermission(new PermissionCheckDTO().setAdminId(adminId).setPermissions(Arrays.asList(permissions)))
                .checkError();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清空 SecurityContext
        AdminSecurityContextHolder.clear();
    }

}
