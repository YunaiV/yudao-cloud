package cn.iocoder.mall.admin.api.constant;

/**
 * 错误码枚举类
 *
 * 用户中心，使用 1-002-000-000 段
 */
public enum AdminErrorCodeEnum {

    // ========== OAUTH2 模块 ==========
    OAUTH2_UNKNOWN(1002001000, "未知错误"), // 预留
//    OAUTH2_INVALID_GRANT_BAD_CREDENTIALS(1001001001, "密码不正确"), // 暂时没用到
//    OAUTH2_INVALID_GRANT_USERNAME_NOT_FOUND(1001001002, "账号不存在"), // 暂时没用到
//    OAUTH2_INVALID_GRANT(1001001010, ""), // 预留
    OAUTH_INVALID_TOKEN_NOT_FOUND(1002001011, "访问令牌不存在"),
    OAUTH_INVALID_TOKEN_EXPIRED(1002001012, "访问令牌已过期"),
    OAUTH_INVALID_TOKEN_INVALID(1002001013, "访问令牌已失效"),
    OAUTH_INVALID_PERMISSION(1002001014, "没有该操作权限"), // TODO 芋艿，临时放在 OAUTH2 模块，理论来说，OAUTH2 只做认证，不做鉴权。

    OAUTH_INVALID_TOKEN(1002001020, ""), // 预留

    // ========== 管理员模块 ==========
    ADMIN_USERNAME_NOT_REGISTERED(1002002000, "账号不存在"),
    ADMIN_PASSWORD_ERROR(1002002001, "密码不正确"),
    ADMIN_IS_DISABLE(1002002002, "账号被禁用");

    private final int code;
    private final String message;

    AdminErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}