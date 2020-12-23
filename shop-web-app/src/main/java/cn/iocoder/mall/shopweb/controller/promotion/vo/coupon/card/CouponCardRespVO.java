package cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("优惠劵 Response VO")
@Data
@Accessors(chain = true)
public class CouponCardRespVO {

    // ========== 基本信息 BEGIN ==========
    @ApiModelProperty(value = "优惠劵编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "模板编号，自增唯一", required = true, example = "1")
    private Integer templateId;
    @ApiModelProperty(value = "优惠劵名", required = true, example = "大保剑")
    private String title;
    @ApiModelProperty(value = "优惠码状态", required = true, example = "参见 CouponCardStatusEnum 枚举")
    private Integer status;

    // ========== 基本信息 END ==========

    // ========== 使用规则 BEGIN ==========
    @ApiModelProperty(value = "是否设置满多少金额可用，单位：分", required = true)
    private Integer priceAvailable;
    @ApiModelProperty(value = "固定日期-生效开始时间", required = true)
    private Date validStartTime;
    @ApiModelProperty(value = "固定日期-生效结束时间", required = true)
    private Date validEndTime;
    // ========== 使用规则 END ==========

    // ========== 使用效果 BEGIN ==========
    @ApiModelProperty(value = "优惠类型", required = true, example = "参见 CouponTemplatePreferentialTypeEnum 枚举")
    private Integer preferentialType;
    @ApiModelProperty(value = "折扣百分比")
    private Integer percentOff;
    @ApiModelProperty(value = "优惠金额，单位：分")
    private Integer priceOff;
    @ApiModelProperty(value = "折扣上限")
    private Integer discountPriceLimit;
    // ========== 使用效果 END ==========

    // ========== 使用情况 BEGIN ==========

    // ========== 使用情况 END ==========

}
