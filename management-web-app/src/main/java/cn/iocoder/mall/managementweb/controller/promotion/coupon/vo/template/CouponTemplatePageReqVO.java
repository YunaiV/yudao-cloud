package cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("优惠劵（码）模板分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CouponTemplatePageReqVO extends PageParam {
    
    @ApiModelProperty(value = "类型", example = "1", notes = "参考 CouponTemplateTypeEnum 枚举")
    private Integer type;
    @ApiModelProperty(value = "标题", example = "优惠劵牛逼")
    private String title;
    @ApiModelProperty(value = "状态", example = "1", notes = "参考 CouponTemplateStatusEnum 枚举")
    private Integer status;
    @ApiModelProperty(value = "优惠类型", example = "1", notes = "参见 PreferentialTypeEnum 枚举")
    private Integer preferentialType;

}
