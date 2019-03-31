package cn.iocoder.mall.promotion.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

import java.util.Date;

/**
 * 优惠码
 */
public class CouponCodeDO extends BaseDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 模板编号 {@link CouponTemplateDO} 的 id
     */
    private Integer templateId;
    /**
     * 优惠码
     */
    private Integer code;
    /**
     * 领取时间
     */
    private Date takeTime;
    /**
     * 领取用户编号
     */
    private Integer userId;
    /**
     * 领取的优惠劵编号
     */
    private Integer couponId;

    public Integer getId() {
        return id;
    }

    public CouponCodeDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public CouponCodeDO setTemplateId(Integer templateId) {
        this.templateId = templateId;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public CouponCodeDO setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public CouponCodeDO setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public CouponCodeDO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public CouponCodeDO setCouponId(Integer couponId) {
        this.couponId = couponId;
        return this;
    }
}