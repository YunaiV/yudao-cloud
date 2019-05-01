package cn.iocoder.mall.admin.sdk.interceptor;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.OAuth2Service;
import cn.iocoder.mall.admin.api.bo.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContext;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 安全拦截器
 */
@Component
public class AdminSecurityInterceptor extends HandlerInterceptorAdapter {

    @Reference(validation = "true")
    @Autowired(required = false) // TODO 芋艿，初始化时，会存在 spring boot 启动时，服务无法引用的情况，先暂时这么解决。
    private OAuth2Service oauth2Service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 校验访问令牌是否正确。若正确，返回授权信息
        String accessToken = HttpUtil.obtainAccess(request);
        OAuth2AuthenticationBO authentication = null;
        if (accessToken != null) {
            CommonResult<OAuth2AuthenticationBO> result = oauth2Service.checkToken(accessToken);
            if (result.isError()) { // TODO 芋艿，如果访问的地址无需登录，这里也不用抛异常
                throw new ServiceException(result.getCode(), result.getMessage());
            }
            authentication = result.getData();
            // 添加到 AdminSecurityContext
            AdminSecurityContext context = new AdminSecurityContext(authentication.getAdminId(), authentication.getRoleIds());
            AdminSecurityContextHolder.setContext(context);
            // 同时也记录管理员编号到 AdminAccessLogInterceptor 中。因为：
            // AdminAccessLogInterceptor 需要在 AdminSecurityInterceptor 之前执行，这样记录的访问日志才健全
            // AdminSecurityInterceptor 执行后，会移除 AdminSecurityContext 信息，这就导致 AdminAccessLogInterceptor 无法获得管理员编号
            // 因此，这里需要进行记录
            if (authentication.getAdminId() != null) {
                AdminAccessLogInterceptor.setAdminId(authentication.getAdminId());
            }
        } else {
            String url = request.getRequestURI();
            if (!url.equals("/admin/passport/login")) { // TODO 临时写死。非登陆接口，必须已经认证身份，不允许匿名访问
                throw new ServiceException(AdminErrorCodeEnum.OAUTH_NOT_LOGIN.getCode(), AdminErrorCodeEnum.OAUTH_NOT_LOGIN.getMessage());
            }
        }
        // 校验是否需要已授权
        checkPermission(request, authentication);
        // 返回成功
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清空 SecurityContext
        AdminSecurityContextHolder.clear();
    }

    private void checkPermission(HttpServletRequest request, OAuth2AuthenticationBO authentication) {
        Integer adminId = authentication != null ? authentication.getAdminId() : null;
        Set<Integer> roleIds = authentication != null ? authentication.getRoleIds() : null;
        String url = request.getRequestURI();
        CommonResult<Boolean> result = oauth2Service.checkPermission(adminId, roleIds, url);
        if (result.isError()) {
            throw new ServiceException(result.getCode(), result.getMessage());
        }
    }

}
