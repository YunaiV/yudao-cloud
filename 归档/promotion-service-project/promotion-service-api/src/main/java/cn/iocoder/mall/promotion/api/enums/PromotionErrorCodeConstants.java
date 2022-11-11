package cn.iocoder.mall.promotion.api.enums;

import cn.iocoder.common.framework.exception.ErrorCode;

/**
 * 错误码枚举类
 *
 * 营销系统，使用 1-006-000-000 段
 */
public interface PromotionErrorCodeConstants {

    // ========== COUPON TEMPLATE 模块 ==========
    ErrorCode COUPON_TEMPLATE_NOT_EXISTS = new ErrorCode(1006002000, "优惠劵模板（码）不存在");
    ErrorCode COUPON_TEMPLATE_STATUS_NOT_ENABLE = new ErrorCode(1006002004, "优惠劵模板（码）未开启");
    ErrorCode COUPON_TEMPLATE_TOTAL_NOT_ENOUGH = new ErrorCode(1006002005, "优惠劵（码）模板的发放量不足");
    ErrorCode COUPON_TEMPLATE_CARD_ADD_EXCEED_QUOTA = new ErrorCode(1006002006, "优惠劵领取到达上限");

    // ========== COUPON CARD 模块 ==========
    ErrorCode COUPON_CARD_NOT_EXISTS = new ErrorCode(1006003000, "优惠劵不存在");
    ErrorCode COUPON_CARD_STATUS_NOT_USED = new ErrorCode( 1006003004, "优惠劵不处于已使用状态");

}
