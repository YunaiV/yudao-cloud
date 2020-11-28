package cn.iocoder.mall.shopweb.controller.pay.vo.transaction;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.payservice.enums.PayChannelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("支付交易提交 Request VO")
@Data
@Accessors(chain = true)
public class PayTransactionSubmitReqVO {

    @ApiModelProperty(value = "应用编号", required = true, example = "POd4RC6a")
    @NotEmpty(message = "应用编号不能为空")
    private String appId;

    @ApiModelProperty(value = "订单号", required = true, example = "1024")
    @NotEmpty(message = "订单号不能为空")
    private String orderId;

    @ApiModelProperty(value = "支付渠道", required = true, example = "1", notes = "参见 PayChannelEnum 枚举")
    @InEnum(value = PayChannelEnum.class, message = "支付渠道必须是 {value}")
    @NotNull(message = "支付渠道")
    private Integer payChannel;

}
