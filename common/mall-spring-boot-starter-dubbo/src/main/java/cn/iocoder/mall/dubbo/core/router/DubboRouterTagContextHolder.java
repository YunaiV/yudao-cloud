package cn.iocoder.mall.dubbo.core.router;

/**
 * Dubbo 路由 Tag 的上下文
 *
 * @see cn.iocoder.mall.dubbo.core.filter.DubboRouterTagFilter
 * @see cn.iocoder.mall.dubbo.core.web.DubboRouterTagWebInterceptor
 */
public class DubboRouterTagContextHolder {

    private static ThreadLocal<String> tagContext = new ThreadLocal<>();

    public static void setTag(String tag) {
        tagContext.set(tag);
    }

    public static String getTag() {
        return tagContext.get();
    }

    public static void clear() {
        tagContext.remove();
    }

}
