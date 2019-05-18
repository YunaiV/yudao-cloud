package cn.iocoder.mall.pay.api.dto.transaction;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.pay.api.constant.PayChannelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("支付交易提交 DTO")
@Data
@Accessors(chain = true)
public class PayTransactionSubmitDTO {

    @ApiModelProperty(value = "应用编号", required = true, example = "POd4RC6a")
    @NotEmpty(message = "应用编号不能为空")
    private String appId;

    @ApiModelProperty(value = "发起交易的 IP", required = true, example = "192.168.10.1", hidden = true) // hidden 的原因是，Service DTO 自己传入，无需暴露的 Controller API 里
    @NotEmpty(message = "IP 不能为空")
    private String createIp;

    @ApiModelProperty(value = "订单号", required = true, example = "1024")
    @NotEmpty(message = "订单号不能为空")
    private String orderId;

    @ApiModelProperty(value = "支付渠道", required = true, example = "1", notes = "参见 PayChannelEnum 枚举")
    @InEnum(value = PayChannelEnum.class, message = "支付渠道必须是 {value}")
    @NotNull(message = "支付渠道")
    private Integer payChannel;

}
