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


    // ========== COUPON TEMPLATE 模块 ==========
    PRODUCT_TEMPLATE_NOT_EXISTS(1006002000, "优惠劵模板（码）不存在"),
    PRODUCT_TEMPLATE_NOT_CARD(1006002001, "不是优惠劵模板"),
    PRODUCT_TEMPLATE_NOT_CODE(1006002002, "不是优惠码模板"),
    PRODUCT_TEMPLATE_TOTAL_CAN_NOT_REDUCE(1006002003, "优惠劵（码）模板的发放数量不能减小"),

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
