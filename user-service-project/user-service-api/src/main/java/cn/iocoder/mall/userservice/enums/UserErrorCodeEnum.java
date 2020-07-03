package cn.iocoder.mall.userservice.enums;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;

/**
 * 错误码枚举类
 *
 * system 系统，使用 1-002-000-000 段
 */
public enum UserErrorCodeEnum implements ServiceExceptionUtil.Enumerable<UserErrorCodeEnum> {

    // ========== OAUTH2 模块 ==========
    OAUTH2_UNKNOWN(1001001000, "未知错误"), // 预留
    // 预留 1001001001 ~ 1001001099 错误码，方便前端
    OAUTH2_ACCESS_TOKEN_NOT_FOUND(1001001001, "访问令牌不存在"),
    OAUTH2_ACCESS_TOKEN_TOKEN_EXPIRED(1001001002, "访问令牌已过期"),
    OAUTH2_ACCESS_TOKEN_INVALID(1001001003, "访问令牌已失效"),
    OAUTH2_NOT_AUTHENTICATE(1001001004, "账号未登陆"),
    OAUTH2_REFRESH_TOKEN_NOT_FOUND(1001001005, "刷新令牌不存在"),
    OAUTH_REFRESH_TOKEN_EXPIRED(1001001006, "访问令牌已过期"),
    OAUTH_REFRESH_TOKEN_INVALID(1001001007, "刷新令牌已失效"),
    // 其它 1001001100 开始
    OAUTH2_ACCOUNT_NOT_FOUND(1001001100, "账号不存在"),
    OAUTH2_ACCOUNT_PASSWORD_ERROR(1001001101, "密码不正确"),

    // ========== 用户手机验证码模块 ==========
    USER_SMS_CODE_NOT_FOUND(1001001200, "验证码不存在"),
    USER_SMS_CODE_EXPIRED(1001001201, "验证码已过期"),
    USER_SMS_CODE_USED(1001001202, "验证码已使用"),
    USER_SMS_CODE_NOT_CORRECT(1001001203, "验证码不正确"),
    USER_SMS_CODE_EXCEED_SEND_MAXIMUM_QUANTITY_PER_DAY(1001001204, "超过每日短信发送数量"),
    USER_SMS_CODE_SEND_TOO_FAST(1001001205, "短信发送过于频率"),

    // ========== 用户地址 ==========
    USER_ADDRESS_NOT_EXISTENT(1001004000, "用户地址不存在!"),
    USER_ADDRESS_IS_DELETED(1001004001, "用户地址已被删除!"),
    USER_GET_ADDRESS_NOT_EXISTS(1001004002, "获取的地址不存在!"),

    // ========== 用户信息模块 1004004100 ==========
    USER_NOT_EXISTS(1004004100, "用户不存在"),
    USER_STATUS_NOT_EXISTS(1004004101, "用户状态不存在"),
    USER_STATUS_EQUALS(1004004101, "用户已经是该状态"),

    ;


    private final int code;
    private final String message;

    UserErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    // TODO: 2020-05-22 封装成start的时候，直接在start中定义一个统一的枚举，从中取值；
    @Override
    public int getGroup() {
        return 0;
    }


}
