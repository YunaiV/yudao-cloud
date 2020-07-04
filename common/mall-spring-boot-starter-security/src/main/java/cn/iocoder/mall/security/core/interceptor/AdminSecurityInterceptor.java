package cn.iocoder.mall.security.core.interceptor;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.core.context.AdminSecurityContext;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import cn.iocoder.mall.system.rpc.api.admin.AdminRPC;
import cn.iocoder.mall.system.rpc.response.admin.AdminResponse;
import cn.iocoder.mall.web.core.util.CommonWebUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum.ADMIN_NOT_FOUND;

public class AdminSecurityInterceptor extends HandlerInterceptorAdapter {

    @Reference(validation = "true", version = "${dubbo.consumer.AdminRPC.version}")
    private AdminRPC adminRPC;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Integer accountId = CommonWebUtil.getUserId(request);
        if (accountId != null) {
            // 获得 Admin 信息
            CommonResult<AdminResponse> adminResult = adminRPC.getAdminByAccountId(accountId);
            if (adminResult.isError()) {
                throw ServiceExceptionUtil.exception(adminResult);
            }
            if (adminResult.getData() == null) {
                throw ServiceExceptionUtil.exception(ADMIN_NOT_FOUND);
            }
            // 设置到 SecurityContext 中
            AdminResponse adminResponse = adminResult.getData();
            AdminSecurityContext context = new AdminSecurityContext().setAdminId(adminResponse.getId())
                    .setAccountId(accountId);
            AdminSecurityContextHolder.setContext(context);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清空 SecurityContext
        AdminSecurityContextHolder.clear();
    }

}
