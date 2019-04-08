package cn.iocoder.mall.cart.api.constant;

/**
 * 错误码枚举类
 *
 * 购物车系统，使用 1-005-000-000 段
 */
public enum CartErrorCodeEnum {

    ;

    private final int code;
    private final String message;

    CartErrorCodeEnum(int code, String message) {
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
