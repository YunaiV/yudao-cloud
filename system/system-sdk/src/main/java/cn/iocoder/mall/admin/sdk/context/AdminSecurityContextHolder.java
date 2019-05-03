package cn.iocoder.mall.admin.sdk.context;

/**
 * {@link AdminSecurityContext} Holder
 *
 * 参考 spring security 的 ThreadLocalSecurityContextHolderStrategy 类，简单实现。
 */
public class AdminSecurityContextHolder {

    private static final ThreadLocal<AdminSecurityContext> securityContext = new ThreadLocal<AdminSecurityContext>();

    public static void setContext(AdminSecurityContext context) {
        securityContext.set(context);
    }

    public static AdminSecurityContext getContext() {
        AdminSecurityContext ctx = securityContext.get();
        // 为空时，设置一个空的进去
        if (ctx == null) {
            ctx = new AdminSecurityContext(null, null);
            securityContext.set(ctx);
        }
        return ctx;
    }

    public static void clear() {
        securityContext.remove();
    }

}