package cn.iocoder.mall.security.core.interceptor;

import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.core.context.UserSecurityContext;
import cn.iocoder.mall.security.core.context.UserSecurityContextHolder;
import cn.iocoder.mall.system.rpc.api.user.UserRPC;
import cn.iocoder.mall.system.rpc.response.user.UserResponse;
import cn.iocoder.mall.web.core.util.CommonWebUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSecurityInterceptor extends HandlerInterceptorAdapter {

    @Reference(validation = "true", version = "${dubbo.consumer.UserRPC.version}")
    private UserRPC userRPC;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Integer accountId = CommonWebUtil.getUserId(request);
        if (accountId != null) {
            // 获得 Admin 信息
            CommonResult<UserResponse> userResult = userRPC.getUserByAccountId(accountId);
            if (userResult.isError()) {
                throw ServiceExceptionUtil.exception(userResult);
            }
            if (userResult.getData() == null) {
                throw ExceptionUtil.getServiceException(null); // TODO 需要完善
            }
            // 设置到 SecurityContext 中
            UserResponse userResponse = userResult.getData();
            UserSecurityContext context = new UserSecurityContext().setUserId(userResponse.getId());
            UserSecurityContextHolder.setContext(context);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清空 SecurityContext
        UserSecurityContextHolder.clear();
    }

}
