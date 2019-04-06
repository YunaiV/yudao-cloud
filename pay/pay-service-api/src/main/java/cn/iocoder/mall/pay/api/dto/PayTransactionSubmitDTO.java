package cn.iocoder.mall.pay.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 支付交易提交 DTO
 */
@Data
@Accessors(chain = true)
public class PayTransactionSubmitDTO {

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
     * 业务线的订单编号
     */
    @NotEmpty(message = "订单号不能为空")
    private String orderId;
    /**
     * 支付渠道
     */
    @NotNull(message = "支付渠道")
    private Integer payChannel;

}
