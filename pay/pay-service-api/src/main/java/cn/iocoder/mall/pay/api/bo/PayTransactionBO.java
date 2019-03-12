package cn.iocoder.mall.pay.api.bo;

import java.util.Date;

/**
 * 支付交易 BO
 */
public class PayTransactionBO {

    /**
     * 编号，自增
     */
    private Integer id;
    /**
     * 应用编号
     */
    private String appId;
    /**
     * 发起交易的 IP
     */
    private String createIp;
    /**
     * 业务线的订单编号
     *
     * 1. 使用 String 的原因是，业务线可能使用 String 做为编号
     * 2. 每个 appId 下，orderId 唯一
     */
    private String orderId;
    /**
     * 订单商品名
     */
    private String orderSubject;
    /**
     * 订单商品描述
     */
    private String orderDescription;
    /**
     * 订单备注
     */
    private String orderMemo;
    /**
     * 支付金额，单位：分。
     */
    private Integer price;
    /**
     * 订单状态
     *
     * @see cn.iocoder.mall.pay.api.constant.PayTransactionStatusEnum
     */
    private Integer status;
    /**
     * 交易过期时间
     */
    private Date expireTime;
    /**
     * 回调业务线完成时间
     */
    private Date finishTime;

    /**
     * 成功支付的交易拓展编号
     */
    private Integer extensionId;
    /**
     * 支付成功的支付渠道
     *
     * @see cn.iocoder.mall.pay.api.constant.PayChannelEnum
     */
    private Integer payChannel;
    /**
     * 第三方支付成功的时间
     */
    private Date paymentTime;
    /**
     * 收到第三方系统通知的时间
     *
     * 一般情况下，即第三方系统的异步通知
     */
    private Date notifyTime;
    /**
     * 第三方的流水号
     */
    private String tradeNo;
    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public PayTransactionBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public PayTransactionBO setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getCreateIp() {
        return createIp;
    }

    public PayTransactionBO setCreateIp(String createIp) {
        this.createIp = createIp;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public PayTransactionBO setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderSubject() {
        return orderSubject;
    }

    public PayTransactionBO setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
        return this;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public PayTransactionBO setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public String getOrderMemo() {
        return orderMemo;
    }

    public PayTransactionBO setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public PayTransactionBO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public PayTransactionBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public PayTransactionBO setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public PayTransactionBO setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public Integer getExtensionId() {
        return extensionId;
    }

    public PayTransactionBO setExtensionId(Integer extensionId) {
        this.extensionId = extensionId;
        return this;
    }

    public Integer getPayChannel() {
        return payChannel;
    }

    public PayTransactionBO setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
        return this;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public PayTransactionBO setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public PayTransactionBO setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
        return this;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public PayTransactionBO setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public PayTransactionBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

}