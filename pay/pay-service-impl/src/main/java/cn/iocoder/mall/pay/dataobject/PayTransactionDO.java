package cn.iocoder.mall.pay.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

import java.util.Date;

/**
 * 支付交易 DO
 */
public class PayTransactionDO extends BaseDO {

    /**
     * 编号，自增
     */
    private Integer id;
    /**
     * 应用编号
     *
     * 不同业务线分配不同的 appId
     * 举个例子，
     * 1. 电商系统的订单，appId = 1024
     * 2. 活动系统的订单，appId = 2048
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
     * 订单状态
     *
     * 0 ： 等待支付
     * 1 ： 待付款完成
     * 2 ： 该笔交易已关闭 TODO 交易关闭，是不是订单系统触发
     * -1 ： 支付失败 TODO 怎么触发
     */
    private Integer orderStatus;
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
     *
     * TODO 暂时不考虑货币类型。
     */
    private Integer price;
    /**
     * 交易过期时间
     */
    private Integer expireTime;

    // TODO return url
    // TODO notify url

    /**
     * 支付成功的支付渠道
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

    public Integer getId() {
        return id;
    }

    public PayTransactionDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public PayTransactionDO setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getCreateIp() {
        return createIp;
    }

    public PayTransactionDO setCreateIp(String createIp) {
        this.createIp = createIp;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public PayTransactionDO setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public PayTransactionDO setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public PayTransactionDO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getPayChannel() {
        return payChannel;
    }

    public PayTransactionDO setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
        return this;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public PayTransactionDO setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public PayTransactionDO setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public PayTransactionDO setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
        return this;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public PayTransactionDO setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }

    public String getOrderSubject() {
        return orderSubject;
    }

    public PayTransactionDO setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
        return this;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public PayTransactionDO setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public String getOrderMemo() {
        return orderMemo;
    }

    public PayTransactionDO setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
        return this;
    }
}