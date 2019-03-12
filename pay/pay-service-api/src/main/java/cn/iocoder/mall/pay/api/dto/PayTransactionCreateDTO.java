package cn.iocoder.mall.pay.api.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 支付交易创建 DTO
 */
public class PayTransactionCreateDTO {

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
     * 订单商品名
     */
    @NotEmpty(message = "商品名不能为空")
    @Size(max = 32, message = "商品名不能超过32")
    private String orderSubject;
    /**
     * 订单商品描述
     */
    @NotEmpty(message = "商品描述不能为空")
    @Size(max = 128, message = "商品描述长度不能超过128")
    private String orderDescription;
    /**
     * 订单备注
     */
    @Size(max = 256, message = "商品描述长度不能超过256")
    private String orderMemo;
    /**
     * 支付金额，单位：分。
     */
    @NotNull(message = "金额不能为空")
    @DecimalMin(value = "0", inclusive = false, message = "金额必须大于零")
    private Integer price;
    /**
     * 交易过期时间
     */
    @NotNull(message = "交易过期时间不能为空")
    private Date expireTime;

    public String getAppId() {
        return appId;
    }

    public PayTransactionCreateDTO setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getCreateIp() {
        return createIp;
    }

    public PayTransactionCreateDTO setCreateIp(String createIp) {
        this.createIp = createIp;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public PayTransactionCreateDTO setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderSubject() {
        return orderSubject;
    }

    public PayTransactionCreateDTO setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
        return this;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public PayTransactionCreateDTO setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public String getOrderMemo() {
        return orderMemo;
    }

    public PayTransactionCreateDTO setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public PayTransactionCreateDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public PayTransactionCreateDTO setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
        return this;
    }

}