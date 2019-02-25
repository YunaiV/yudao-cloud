package cn.iocoder.mall.user.sdk.context;

/**
 * {@link SecurityContext} Holder
 *
 * 参考 spring security 的 ThreadLocalSecurityContextHolderStrategy 类，简单实现。
 */
public class SecurityContextHolder {

    private static final ThreadLocal<SecurityContext> securityContext = new ThreadLocal<SecurityContext>();

    public static void setContext(SecurityContext context) {
        securityContext.set(context);
    }

    public static SecurityContext getContext() {
        SecurityContext ctx = securityContext.get();
        // 为空时，设置一个空的进去
        if (ctx == null) {
            ctx = new SecurityContext(null);
            securityContext.set(ctx);
        }
        return ctx;
    }

    public static void clear() {
        securityContext.remove();
    }

}