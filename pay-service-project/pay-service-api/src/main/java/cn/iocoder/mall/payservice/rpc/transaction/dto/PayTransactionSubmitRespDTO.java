package cn.iocoder.mall.payservice.rpc.transaction.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 支付交易提交 Response DTO
 */
@Data
@Accessors(chain = true)
public class PayTransactionSubmitRespDTO implements Serializable {

    /**
     * 支付交易拓展单编号
     */
    private Integer id;

    /**
     * 调用三方平台的响应结果
     */
    private String invokeResponse;

}
