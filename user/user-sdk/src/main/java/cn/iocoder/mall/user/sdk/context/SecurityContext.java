package cn.iocoder.mall.user.sdk.context;

/**
 * Security 上下文
 */
public class SecurityContext {

    private final Long uid;

    public SecurityContext(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

}