package cn.iocoder.mall.security.user.core.context;

/**
 * {@link UserSecurityContext} Holder
 *
 * 参考 spring security 的 ThreadLocalSecurityContextHolderStrategy 类，简单实现。
 */
public class UserSecurityContextHolder {

    private static final ThreadLocal<UserSecurityContext> SECURITY_CONTEXT = new ThreadLocal<UserSecurityContext>();

    public static void setContext(UserSecurityContext context) {
        SECURITY_CONTEXT.set(context);
    }

    public static UserSecurityContext getContext() {
        UserSecurityContext ctx = SECURITY_CONTEXT.get();
        // 为空时，设置一个空的进去
        if (ctx == null) {
            ctx = new UserSecurityContext();
            SECURITY_CONTEXT.set(ctx);
        }
        return ctx;
    }

    public static Integer getUserId() {
        UserSecurityContext ctx = SECURITY_CONTEXT.get();
        return ctx != null ? ctx.getUserId() : null;
    }

    public static void clear() {
        SECURITY_CONTEXT.remove();
    }

}
