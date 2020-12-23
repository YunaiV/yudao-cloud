package cn.iocoder.mall.dubbo.core.web;

import cn.iocoder.common.framework.util.OSUtils;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.dubbo.core.cluster.interceptor.DubboConsumerRouterTagClusterInterceptor;
import cn.iocoder.mall.dubbo.core.filter.DubboProviderRouterTagFilter;
import cn.iocoder.mall.dubbo.core.router.DubboRouterTagContextHolder;
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

    private static final String HOST_NAME_VALUE = "${HOSTNAME}";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tag = request.getHeader(HEADER_DUBBO_TAG);
        if (StringUtils.hasText(tag)) {
            // 特殊逻辑，解决 IDEA Rest Client 不支持环境变量的读取，所以就服务器来做
            if (HOST_NAME_VALUE.equals(tag)) {
                tag = OSUtils.getHostName();
            }
            // 设置到 DubboRouterTagContextHolder 上下文
            DubboRouterTagContextHolder.setTag(tag);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        DubboRouterTagContextHolder.clear();
    }

}
