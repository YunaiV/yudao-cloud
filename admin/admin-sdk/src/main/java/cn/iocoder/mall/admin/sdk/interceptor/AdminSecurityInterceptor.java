package cn.iocoder.mall.admin.sdk.interceptor;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.OAuth2Service;
import cn.iocoder.mall.admin.api.bo.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContext;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
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

    @Reference
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
            // 添加到 SecurityContext
            AdminSecurityContext context = new AdminSecurityContext(authentication.getAdminId(), authentication.getRoleIds());
            AdminSecurityContextHolder.setContext(context);
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