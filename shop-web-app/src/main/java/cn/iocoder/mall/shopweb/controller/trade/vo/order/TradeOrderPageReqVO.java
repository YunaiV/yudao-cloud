package cn.iocoder.mall.shopweb.controller.trade.vo.order;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("交易订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class TradeOrderPageReqVO extends PageParam {

    @ApiModelProperty(value = "订单状态", example = "1", notes = "参见 TradeOrderStatusEnum 枚举")
    private Integer orderStatus;

}
