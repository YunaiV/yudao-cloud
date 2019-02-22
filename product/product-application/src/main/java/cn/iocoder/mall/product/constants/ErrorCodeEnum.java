package cn.iocoder.mall.product.constants;

/**
 * 错误码枚举类
 *
 * 系统级异常，使用 2-001-000-000 段
 */
public enum ErrorCodeEnum {

    SYS_ERROR(2001001000, "服务端发生异常"),
    MISSING_REQUEST_PARAM_ERROR(2001001001, "参数缺失"),

    ;

    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    ;

}
