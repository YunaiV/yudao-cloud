package cn.iocoder.mall.sentry.resolver;

import io.sentry.spring.SentryExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认什么也不做的 SentryExceptionResolver
 *
 * @author Hccake 2020/8/6
 * @version 1.0
 */
public class DoNothingExceptionResolver extends SentryExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        // do nothing here

        // null = run other HandlerExceptionResolvers to actually handle the exception
        return null;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}