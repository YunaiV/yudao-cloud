package cn.iocoder.mall.promotion.api.enums;

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
    COUPON_TEMPLATE_NOT_EXISTS(1006002000, "优惠劵模板（码）不存在"),
    COUPON_TEMPLATE_NOT_CARD(1006002001, "不是优惠劵模板"),
    COUPON_TEMPLATE_NOT_CODE(1006002002, "不是优惠码模板"),
    COUPON_TEMPLATE_TOTAL_CAN_NOT_REDUCE(1006002003, "优惠劵（码）模板的发放数量不能减小"),
    COUPON_TEMPLATE_STATUS_NOT_ENABLE(1006002004, "优惠劵模板（码）未开启"),
    COUPON_TEMPLATE_TOTAL_NOT_ENOUGH(1006002005, "优惠劵（码）模板的发放量不足"),
    COUPON_TEMPLATE_CARD_ADD_EXCEED_QUOTA(1006002006, "优惠劵领取到达上限"),

    // ========== COUPON CARD 模块 ==========
    COUPON_CARD_NOT_EXISTS(1006003000, "优惠劵不存在"),
    COUPON_CARD_ERROR_USER(1006003001, "优惠劵不属于当前用户"),
    COUPON_CARD_NOT_MATCH(1006003002, "优惠劵不匹配，无法使用"),
    COUPON_CARD_STATUS_NOT_UNUSED(1006003003, "优惠劵不处于待使用状态"),
    COUPON_CARD_STATUS_NOT_USED(1006003004, "优惠劵不处于已使用状态"),
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
