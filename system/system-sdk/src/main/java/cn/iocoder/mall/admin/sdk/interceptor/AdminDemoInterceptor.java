package cn.iocoder.mall.admin.sdk.interceptor;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.admin.api.constant.AdminConstants;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Admin 演示拦截器
 *
 * 这是个比较“奇怪”的拦截器，用于演示的管理员账号，禁止使用 POST 请求，从而实现即达到阉割版的演示的效果，又避免影响了数据
 */
@Component
public class AdminDemoInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (AdminConstants.USERNAME_DEMO.equals(AdminSecurityContextHolder.getContext().getUsername())
            && request.getMethod().equalsIgnoreCase(HttpMethod.POST.toString())) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_DEMO_CAN_NOT_WRITE.getCode());
        }
        return true;
    }

}
