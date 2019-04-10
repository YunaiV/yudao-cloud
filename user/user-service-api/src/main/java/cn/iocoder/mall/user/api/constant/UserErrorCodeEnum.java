package cn.iocoder.mall.user.api.constant;

/**
 * 错误码枚举类
 *
 * 用户系统，使用 1-001-000-000 段
 */
public enum UserErrorCodeEnum {

    // ========== OAUTH2 模块 ==========
    OAUTH2_UNKNOWN(1001001000, "未知错误"), // 预留
    OAUTH2_INVALID_GRANT_BAD_CREDENTIALS(1001001001, "密码不正确"), // 暂时没用到
    OAUTH2_INVALID_GRANT_USERNAME_NOT_FOUND(1001001002, "账号不存在"), // 暂时没用到
    OAUTH2_INVALID_GRANT(1001001010, ""), // 预留
    OAUTH_INVALID_TOKEN_NOT_FOUND(1001001011, "访问令牌不存在"),
    OAUTH_INVALID_TOKEN_EXPIRED(1001001012, "访问令牌已过期"),
    OAUTH_INVALID_TOKEN_INVALID(1001001013, "访问令牌已失效"),
    OAUTH_INVALID_TOKEN(1001001020, ""), // 预留

    // ========== 用户模块 ==========
    USER_MOBILE_NOT_REGISTERED(1001002000, "手机号未注册用户"),
    USER_MOBILE_ALREADY_REGISTERED(1001002001, "手机号已经注册用户"),
    USER_NOT_EXISTS(1001002002, "用户不存在"),
    USER_STATUS_EQUALS(1001002003, "账号已经是该状态"),
    USER_MOBILE_EQUALS(1001002004, "账号已经是该手机号"),

    // ========== 手机验证码模块 ==========
    MOBILE_CODE_NOT_FOUND(1001003000, "验证码不存在"),
    MOBILE_CODE_EXPIRED(1001003001, "验证码已过期"),
    MOBILE_CODE_USED(1001003002, "验证码已使用"),
    MOBILE_CODE_NOT_CORRECT(1001003003, "验证码不正确"),
    MOBILE_CODE_EXCEED_SEND_MAXIMUM_QUANTITY_PER_DAY(1001003004, "超过每日短信发送数量"),
    MOBILE_CODE_SEND_TOO_FAST(1001003005, "短信发送过于频率"),

    // ========== 用户地址 ==========
    USER_ADDRESS_NOT_EXISTENT(1001004000, "用户地址不存在!"),
    USER_ADDRESS_IS_DELETED(1001004001, "用户地址已被删除!"),
    USER_GET_ADDRESS_NOT_EXISTS(1001004002, "获取的地址不存在!"),
    ;

    private final int code;
    private final String message;

    UserErrorCodeEnum(int code, String message) {
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