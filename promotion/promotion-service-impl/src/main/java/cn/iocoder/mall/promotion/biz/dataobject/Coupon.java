package cn.iocoder.mall.promotion.biz.dataobject;

import java.util.Date;

/**
 * 优惠劵
 */
public class Coupon {

    // ========== 基本信息 BEGIN ==========
    /**
     * 卡券ID
     */
    private Integer id;
    /**
     * 店铺编号
     */
    private Integer shopId;
    /**
     * 类型
     *
     * 1-优惠劵
     * 2-优惠码
     */
    private Integer type;
    /**
     * 优惠劵（码）分组编号，{@link CouponTemplate} 的 id
     */
    private Integer couponGroupId;
    /**
     * 核销码
     */
    private String verifyCode;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 优惠码状态
     *
     * 1-生效中
     * 2-已失效
     * 3-已过期
     * 4-已删除
     * 5-已使用
     */
    private Integer status;

    // ========== 基本信息 END ==========

    // ========== 领取情况 BEGIN ==========
    /**
     * 是否领取
     */
    private Boolean isTake;
    /**
     * 领取用户编号
     */
    private Integer userId;
    /**
     * 领取时间
     */
    private Date takeTime;
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
     * 2-折扣卷 【优惠劵独有】
     */
    private Integer preferentialType;
    /**
     * 折扣
     */
    private Double discount;
    /**
     * 优惠金额，单位：分。
     */
    private Integer value;
    // ========== 使用效果 END ==========

    // ========== 使用情况 BEGIN ==========
    /**
     * 是否使用
     */
    private Boolean isUsed;
    /**
     * 使用订单号
     */
    private String usedInTid;
    /**
     * 订单中优惠面值，单位：分
     */
    private Integer usedValue;
    /**
     * 使用时间
     */
    private Date usedTime;
    // ========== 使用情况 END ==========

}