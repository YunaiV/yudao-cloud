package cn.iocoder.mall.promotion.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

import java.util.Date;

/**
 * 优惠劵 DO
 */
public class CouponDO extends BaseDO {

    // ========== 基本信息 BEGIN ==========
    /**
     * 优惠劵编号
     */
    private Integer id;
    /**
     * 优惠劵（码）分组编号，{@link CouponTemplateDO} 的 id
     */
    private Integer templateId;
//    /**
//     * 核销码
//     */
//    private String verifyCode;
    /**
     * 优惠码状态
     *
     * 1-生效中
     * 2-已失效
     * 3-已过期
     * 4-已删除
     * 5-已使用
     *
     * TODO 需要讨论下
     */
    private Integer status;

    // ========== 基本信息 END ==========

    // ========== 领取情况 BEGIN ==========
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 领取类型
     *
     * 1 - 用户主动领取
     * 2 - 后台自动发放
     */
    private Integer takeType;
    // ========== 领取情况 END ==========

    // ========== 使用规则 BEGIN ==========
    /**
     * 固定日期-生效开始时间
     */
    private Date validStartTime;
    /**
     * 固定日期-生效结束时间
     */
    private Date validEndTime;
    // ========== 使用规则 END ==========

    // ========== 使用效果 BEGIN ==========
    /**
     * 优惠类型
     *
     * 1-代金卷
     * 2-折扣卷
     */
    private Integer preferentialType;
    /**
     * 折扣
     */
    private Double percentOff;
    /**
     * 优惠金额，单位：分。
     */
    private Integer priceOff;
    /**
     * 折扣上限，仅在 {@link #preferentialType} 等于 2 时生效。
     *
     * 例如，折扣上限为 20 元，当使用 8 折优惠券，订单金额为 1000 元时，最高只可折扣 20 元，而非 80  元。
     */
    private Integer discountPriceLimit;
    // ========== 使用效果 END ==========

    // ========== 使用情况 BEGIN ==========
    /**
     * 是否使用
     */
    private Boolean used;
    /**
     * 使用订单号
     */
    private String usedOrderId;
    /**
     * 订单中优惠面值，单位：分
     */
    private Integer usedPrice;
    /**
     * 使用时间
     */
    private Date usedTime;
    // ========== 使用情况 END ==========

}