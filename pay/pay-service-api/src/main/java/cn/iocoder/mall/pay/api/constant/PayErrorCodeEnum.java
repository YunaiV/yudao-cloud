package cn.iocoder.mall.pay.api.constant;

/**
 * 错误码枚举类
 *
 * 管理员系统，使用 1-004-000-000 段
 */
public enum PayErrorCodeEnum {

    // ========== PAY 模块 ==========
    ;

    private final int code;
    private final String message;

    PayErrorCodeEnum(int code, String message) {
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