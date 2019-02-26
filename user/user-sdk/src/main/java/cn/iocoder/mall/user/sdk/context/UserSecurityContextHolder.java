package cn.iocoder.mall.user.sdk.context;

/**
 * {@link UserSecurityContext} Holder
 *
 * 参考 spring security 的 ThreadLocalSecurityContextHolderStrategy 类，简单实现。
 */
public class UserSecurityContextHolder {

    private static final ThreadLocal<UserSecurityContext> securityContext = new ThreadLocal<UserSecurityContext>();

    public static void setContext(UserSecurityContext context) {
        securityContext.set(context);
    }

    public static UserSecurityContext getContext() {
        UserSecurityContext ctx = securityContext.get();
        // 为空时，设置一个空的进去
        if (ctx == null) {
            ctx = new UserSecurityContext(null);
            securityContext.set(ctx);
        }
        return ctx;
    }

    public static void clear() {
        securityContext.remove();
    }

}