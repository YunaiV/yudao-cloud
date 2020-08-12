package cn.iocoder.mall.promotion.api.enums;

import cn.iocoder.common.framework.exception.ErrorCode;

/**
 * 错误码枚举类
 *
 * 营销系统，使用 1-006-000-000 段
 */
public interface PromotionErrorCodeConstants {

    // ========== Banner 模块 ==========
    ErrorCode BANNER_NOT_EXISTS = new ErrorCode(1006000000, "账号不存在");

    // ========== PRODUCT RECOMMEND 模块 ==========
    ErrorCode PRODUCT_RECOMMEND_NOT_EXISTS = new ErrorCode(1006001000, "商品推荐不存在");
    ErrorCode PRODUCT_RECOMMEND_PRODUCT_NOT_EXISTS = new ErrorCode(1006001001, "商品不存在");
    ErrorCode PRODUCT_RECOMMEND_EXISTS = new ErrorCode(1006001002, "该商品推荐已经存在");


    // ========== COUPON TEMPLATE 模块 ==========
    ErrorCode COUPON_TEMPLATE_NOT_EXISTS = new ErrorCode(1006002000, "优惠劵模板（码）不存在");
    ErrorCode COUPON_TEMPLATE_NOT_CARD = new ErrorCode(1006002001, "不是优惠劵模板");
    ErrorCode COUPON_TEMPLATE_NOT_CODE = new ErrorCode(1006002002, "不是优惠码模板");
    ErrorCode COUPON_TEMPLATE_TOTAL_CAN_NOT_REDUCE = new ErrorCode(1006002003, "优惠劵（码）模板的发放数量不能减小");
    ErrorCode COUPON_TEMPLATE_STATUS_NOT_ENABLE = new ErrorCode(1006002004, "优惠劵模板（码）未开启");
    ErrorCode COUPON_TEMPLATE_TOTAL_NOT_ENOUGH = new ErrorCode(1006002005, "优惠劵（码）模板的发放量不足");
    ErrorCode COUPON_TEMPLATE_CARD_ADD_EXCEED_QUOTA = new ErrorCode(1006002006, "优惠劵领取到达上限");

    // ========== COUPON CARD 模块 ==========
    ErrorCode COUPON_CARD_NOT_EXISTS = new ErrorCode(1006003000, "优惠劵不存在");
    ErrorCode COUPON_CARD_ERROR_USER = new ErrorCode(1006003001, "优惠劵不属于当前用户");
    ErrorCode COUPON_CARD_NOT_MATCH = new ErrorCode(1006003002, "优惠劵不匹配，无法使用");
    ErrorCode COUPON_CARD_STATUS_NOT_UNUSED = new ErrorCode(1006003003, "优惠劵不处于待使用状态");
    ErrorCode COUPON_CARD_STATUS_NOT_USED = new ErrorCode( 1006003004, "优惠劵不处于已使用状态");

    // ========== PRICE 模块 ==========
    ErrorCode PRICE_PRODUCT_SKU_NOT_EXISTS = new ErrorCode(1006004000, "有不存在的商品！");

}
