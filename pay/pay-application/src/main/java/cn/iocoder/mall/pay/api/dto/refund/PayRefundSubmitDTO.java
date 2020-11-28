package cn.iocoder.mall.pay.api.dto.refund;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 支付退款创建 DTO
 */
@Data
@Accessors(chain = true)
public class PayRefundSubmitDTO implements Serializable {

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
     * 退款描述
     */
    @NotEmpty(message = "退款描述不能为空")
    @Length(max = 128, message = "退款描述长度不能超过128")
    private String orderDescription;
    /**
     * 支付金额，单位：分。
     */
    @NotNull(message = "金额不能为空")
    @DecimalMin(value = "0", inclusive = false, message = "金额必须大于零")
    private Integer price;

}
