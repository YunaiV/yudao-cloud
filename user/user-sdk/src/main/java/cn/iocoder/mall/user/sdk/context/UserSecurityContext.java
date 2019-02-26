package cn.iocoder.mall.user.sdk.context;

/**
 * User Security 上下文
 */
public class UserSecurityContext {

    private final Long uid;

    public UserSecurityContext(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

}