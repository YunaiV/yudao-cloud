package cn.iocoder.mall.pay.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 支付交易提交 DTO
 */
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

    public String getAppId() {
        return appId;
    }

    public PayTransactionSubmitDTO setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getCreateIp() {
        return createIp;
    }

    public PayTransactionSubmitDTO setCreateIp(String createIp) {
        this.createIp = createIp;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public PayTransactionSubmitDTO setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getPayChannel() {
        return payChannel;
    }

    public PayTransactionSubmitDTO setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
        return this;
    }

}