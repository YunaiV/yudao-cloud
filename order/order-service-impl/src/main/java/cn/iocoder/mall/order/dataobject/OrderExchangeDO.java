package cn.iocoder.mall.order.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

import java.util.Date;

/**
 * 换货订单
 *
 * @author Sin
 * @time 2019-03-19 19:48
 */
public class OrderExchangeDO extends DeletableDO {

    /**
     * id
     */
    private Integer id;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单 item 编号
     */
    private Integer orderItemId;
    /**
     * 商品id（保存一个冗余，如果一个订单下存在多个商品，会有很大的作用）
     */
    private String skuId;
    /**
     * 换货商品id
     */
    private String exchangeSkuId;
    /**
     * 换货物流id
     */
    private Integer exchangeOrderLogisticsId;
    /**
     * 收件物流id
     */
    private Integer receiverOrderLogisticsId;

    ///
    /// 原因

    /**
     * 原因 (关联字典)
     *
     * {@link cn.iocoder.mall.order.constants.OrderExchangeReasonEnum}
     */
    private Integer orderReasonId;
    /**
     * 原因（如果选择其他，原因保存在这）
     *
     * {@link cn.iocoder.mall.order.constants.OrderExchangeReasonEnum#REASON_000}
     */
    private String reason;

    ///
    /// 时间信息

    /**
     * 创建时间
     * supper baseDO
     */
//    private Date createTime;
    /**
     * 付款时间
     */
    private Date paymentTime;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 收货时间
     */
    private Date receiverTime;
    /**
     * 成交时间
     */
    private Date closingTime;

    ///
    /// 其他

    /**
     * 订单类型
     *
     * - 0、为 Order 订单 （对整个订单退货）
     * - 1、为 OrderItem 订单 （对订单某一个商品退货）
     */
    private Integer orderType;
    /**
     * 状态
     *
     * - 申请换货
     * - 申请成功
     * - 申请失败
     * - 换货中
     * - 换货成功
     */
    private Integer status;

    @Override
    public String toString() {
        return "OrderExchangeDO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", orderItemId=" + orderItemId +
                ", skuId='" + skuId + '\'' +
                ", exchangeSkuId='" + exchangeSkuId + '\'' +
                ", exchangeOrderLogisticsId=" + exchangeOrderLogisticsId +
                ", receiverOrderLogisticsId=" + receiverOrderLogisticsId +
                ", orderReasonId=" + orderReasonId +
                ", reason='" + reason + '\'' +
                ", paymentTime=" + paymentTime +
                ", deliveryTime=" + deliveryTime +
                ", receiverTime=" + receiverTime +
                ", closingTime=" + closingTime +
                ", orderType=" + orderType +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderExchangeDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderExchangeDO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderExchangeDO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public OrderExchangeDO setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
        return this;
    }

    public String getSkuId() {
        return skuId;
    }

    public OrderExchangeDO setSkuId(String skuId) {
        this.skuId = skuId;
        return this;
    }

    public String getExchangeSkuId() {
        return exchangeSkuId;
    }

    public OrderExchangeDO setExchangeSkuId(String exchangeSkuId) {
        this.exchangeSkuId = exchangeSkuId;
        return this;
    }

    public Integer getExchangeOrderLogisticsId() {
        return exchangeOrderLogisticsId;
    }

    public OrderExchangeDO setExchangeOrderLogisticsId(Integer exchangeOrderLogisticsId) {
        this.exchangeOrderLogisticsId = exchangeOrderLogisticsId;
        return this;
    }

    public Integer getReceiverOrderLogisticsId() {
        return receiverOrderLogisticsId;
    }

    public OrderExchangeDO setReceiverOrderLogisticsId(Integer receiverOrderLogisticsId) {
        this.receiverOrderLogisticsId = receiverOrderLogisticsId;
        return this;
    }

    public Integer getOrderReasonId() {
        return orderReasonId;
    }

    public OrderExchangeDO setOrderReasonId(Integer orderReasonId) {
        this.orderReasonId = orderReasonId;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public OrderExchangeDO setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public OrderExchangeDO setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public OrderExchangeDO setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }

    public Date getReceiverTime() {
        return receiverTime;
    }

    public OrderExchangeDO setReceiverTime(Date receiverTime) {
        this.receiverTime = receiverTime;
        return this;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public OrderExchangeDO setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
        return this;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public OrderExchangeDO setOrderType(Integer orderType) {
        this.orderType = orderType;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderExchangeDO setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
