package cn.iocoder.mall.shopweb.controller.trade.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@ApiModel(value = "创建交易订单 VO，基于购物车")
@Data
@Accessors(chain = true)
public class TradeOrderCreateFromCartReqVO {

    @ApiModelProperty(name = "收件地址编号", required = true, example = "1")
    @NotNull(message = "用户地址不能为空")
    private Integer userAddressId;
    @ApiModelProperty(name = "优惠劵编号", example = "1024")
    private Integer couponCardId;
    @ApiModelProperty(name = "备注", example = "1024")
    private String remark;

}
