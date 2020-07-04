package cn.iocoder.mall.security.admin.core.interceptor;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.security.admin.core.context.AdminSecurityContextHolder;
import cn.iocoder.mall.systemservice.enums.SystemErrorCodeEnum;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Admin 演示拦截器
 *
 * 这是个比较“奇怪”的拦截器，用于演示的管理员账号，禁止使用 POST 请求，从而实现即达到阉割版的演示的效果，又避免影响了数据
 */
public class AdminDemoInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 当 Admin 编号等于 0 时，约定为演示账号
        if (Objects.equals(AdminSecurityContextHolder.getAdminId(), 0)
            && request.getMethod().equalsIgnoreCase(HttpMethod.POST.toString())) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.AUTHORIZATION_DEMO_PERMISSION_DENY);
        }
        return true;
    }

}
