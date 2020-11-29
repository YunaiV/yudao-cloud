package cn.iocoder.mall.payservice.rpc.transaction.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 交易支付成功 Request DTO
 */
@Data
@Accessors(chain = true)
public class PayTransactionSuccessReqDTO implements Serializable {

    /**
     * 支付渠道
     */
    private Integer payChannel;
    /**
     * 支付渠道的回调参数
     */
    private String params;

}
