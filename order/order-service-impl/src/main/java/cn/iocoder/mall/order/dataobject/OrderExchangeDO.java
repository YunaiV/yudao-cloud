package cn.iocoder.mall.order.dataobject;

import java.io.Serializable;
import java.util.Date;

/**
 * 换货订单
 *
 * @author Sin
 * @time 2019-03-19 19:48
 */
public class OrderExchangeDO implements Serializable {

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
     * 换货物流id
     */
    private Integer exchangeOrderLogisticsId;
    /**
     * 收件物流id
     */
    private Integer receiverOrderLogisticsId;

    ///
    /// 时间信息

    /**
     * 创建时间
     */
    private Date createTime;
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
                ", exchangeOrderLogisticsId=" + exchangeOrderLogisticsId +
                ", receiverOrderLogisticsId=" + receiverOrderLogisticsId +
                ", createTime=" + createTime +
                ", paymentTime=" + paymentTime +
                ", deliveryTime=" + deliveryTime +
                ", receiverTime=" + receiverTime +
                ", closingTime=" + closingTime +
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

    public Date getCreateTime() {
        return createTime;
    }

    public OrderExchangeDO setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getStatus() {
        return status;
    }

    public OrderExchangeDO setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
