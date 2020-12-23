package cn.iocoder.mall.payservice.rpc.transaction.dto;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.payservice.enums.PayChannelEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 支付交易提交 Request VO
 */
@Data
@Accessors(chain = true)
public class PayTransactionSubmitReqDTO implements Serializable {

    /**
     * 应用编号
     */
    @NotEmpty(message = "应用编号不能为空")
    private String appId;

    /**
     * 发起交易的 IP
     */
    @NotEmpty(message = "IP 不能为空")
    private String createIp;

    /**
     * 订单号
     */
    @NotEmpty(message = "订单号不能为空")
    private String orderId;

    /**
     * 支付渠道
     */
    @InEnum(value = PayChannelEnum.class, message = "支付渠道必须是 {value}")
    @NotNull(message = "支付渠道")
    private Integer payChannel;

}
