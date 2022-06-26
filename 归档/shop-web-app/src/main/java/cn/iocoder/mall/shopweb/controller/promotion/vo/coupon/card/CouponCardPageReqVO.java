package cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.card;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("优惠劵分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CouponCardPageReqVO extends PageParam {

    @ApiModelProperty(value = "优惠码状态", required = true, example = "1", notes = "对应 CouponCardStatusEnum 枚举")
    private Integer status;

}
