package cn.iocoder.mall.user.sdk.context;

/**
 * User Security 上下文
 */
public class UserSecurityContext {

    private final Integer userId;

    public UserSecurityContext(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

}