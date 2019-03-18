package cn.iocoder.mall.user.sdk.interceptor;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.sdk.annotation.PermitAll;
import cn.iocoder.mall.user.sdk.context.UserSecurityContext;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import cn.iocoder.mall.user.service.api.OAuth2Service;
import cn.iocoder.mall.user.service.api.bo.OAuth2AuthenticationBO;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 安全拦截器
 */
@Component
public class UserSecurityInterceptor extends HandlerInterceptorAdapter {

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
            UserSecurityContext context = new UserSecurityContext(authentication.getUserId());
            UserSecurityContextHolder.setContext(context);
            // 同时也记录管理员编号到 AdminAccessLogInterceptor 中。因为：
            // AdminAccessLogInterceptor 需要在 AdminSecurityInterceptor 之前执行，这样记录的访问日志才健全
            // AdminSecurityInterceptor 执行后，会移除 AdminSecurityContext 信息，这就导致 AdminAccessLogInterceptor 无法获得管理员编号
            // 因此，这里需要进行记录
            if (authentication.getUserId() != null) {
                UserAccessLogInterceptor.setUserId(authentication.getUserId());
            }
        }
        // 校验是否需要已授权
        HandlerMethod method = (HandlerMethod) handler;
        boolean isPermitAll = method.hasMethodAnnotation(PermitAll.class);
        if (!isPermitAll && authentication == null) {
            throw new ServiceException(-1, "未授权"); // TODO 这里要改下
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清空 SecurityContext
        UserSecurityContextHolder.clear();
    }

}