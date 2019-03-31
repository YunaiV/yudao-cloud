package cn.iocoder.mall.promotion.api.constant;

/**
 * 错误码枚举类
 *
 * 营销系统，使用 1-006-000-000 段
 */
public enum PromotionErrorCodeEnum {

    // ========== Banner 模块 ==========
    BANNER_NOT_EXISTS(1006000000, "账号不存在"),

    // ========== PRODUCT RECOMMEND 模块 ==========
    PRODUCT_RECOMMEND_NOT_EXISTS(1006001000, "商品推荐不存在"),
    PRODUCT_RECOMMEND_PRODUCT_NOT_EXISTS(1006001001, "商品不存在"),
    PRODUCT_RECOMMEND_EXISTS(1006001002, "该商品推荐已经存在"),

    ;


    private final int code;
    private final String message;

    PromotionErrorCodeEnum(int code, String message) {
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