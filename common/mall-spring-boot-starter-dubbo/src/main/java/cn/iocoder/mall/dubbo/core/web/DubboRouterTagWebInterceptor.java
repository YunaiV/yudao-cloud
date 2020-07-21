package cn.iocoder.mall.dubbo.core.web;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.dubbo.core.cluster.interceptor.DubboConsumerRouterTagClusterInterceptor;
import cn.iocoder.mall.dubbo.core.filter.DubboProviderRouterTagFilter;
import cn.iocoder.mall.dubbo.core.router.DubboRouterTagContextHolder;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Dubbo 路由标签的 Web 拦截器，将请求 Header 中的 {@link #HEADER_DUBBO_TAG} 设置到 {@link DubboRouterTagContextHolder} 中。
 *
 * @see DubboProviderRouterTagFilter
 * @see DubboConsumerRouterTagClusterInterceptor
 */
public class DubboRouterTagWebInterceptor implements HandlerInterceptor {

    private static final String HEADER_DUBBO_TAG = "dubbo-tag";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tag = request.getHeader(HEADER_DUBBO_TAG);
        if (StringUtils.hasText(tag)) {
            DubboRouterTagContextHolder.setTag(tag);
            RpcContext.getContext().setAttachment(CommonConstants.TAG_KEY, tag);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        DubboRouterTagContextHolder.clear();
    }

}
