package cn.iocoder.mall.payservice.rpc.transaction.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 支付交易获得 Request DTO
 */
@Data
@Accessors(chain = true)
public class PayTransactionGetReqDTO implements Serializable {

    /**
     * 应用编号
     */
    @NotEmpty(message = "应用编号不能为空")
    private String appId;

    /**
     * 订单号
     */
    @NotEmpty(message = "订单号不能为空")
    private String orderId;

}
