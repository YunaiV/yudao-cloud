package cn.iocoder.mall.payservice.rpc.app.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 支付应用 Response DTO
 */
@Data
@Accessors(chain = true)
public class PayAppRespDTO implements Serializable {

    /**
     * 应用编号
     */
    private String id;
    /**
     * 应用名
     */
    private String name;
    /**
     * 异步通知地址
     */
    private String payNotifyUrl;
    /**
     * 退款异步通知地址
     */
    private String refundNotifyUrl;
    /**
     * 状态
     */
    private Integer status;

}
