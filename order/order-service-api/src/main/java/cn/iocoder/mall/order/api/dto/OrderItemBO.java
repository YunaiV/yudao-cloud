package cn.iocoder.mall.order.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单 item
 *
 * @author Sin
 * @time 2019-03-28 21:11
 */
public class OrderItemBO implements Serializable {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 商品编号
     */
    private Integer skuId;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 金额(分)
     */
    private Integer price;

    ///
    /// 时间信息

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
     * 是否退货
     *
     * - 1、没有
     * - 2、换货
     * - 3、退货
     * - 4、换货 + 退货
     */
    private Integer hasReturnExchange;
    /**
     * 状态
     *
     * - 1、待付款
     * - 2、待发货
     * - 3、已发货
     * - 4、已完成
     * - 5、已关闭
     */
    private Integer status;

    @Override
    public String toString() {
        return "OrderItemDO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", skuId='" + skuId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", paymentTime=" + paymentTime +
                ", deliveryTime=" + deliveryTime +
                ", receiverTime=" + receiverTime +
                ", closingTime=" + closingTime +
                ", hasReturnExchange=" + hasReturnExchange +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderItemBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderItemBO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderItemBO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public OrderItemBO setSkuId(Integer skuId) {
        this.skuId = skuId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItemBO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderItemBO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public OrderItemBO setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public OrderItemBO setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }

    public Date getReceiverTime() {
        return receiverTime;
    }

    public OrderItemBO setReceiverTime(Date receiverTime) {
        this.receiverTime = receiverTime;
        return this;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public OrderItemBO setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
        return this;
    }

    public Integer getHasReturnExchange() {
        return hasReturnExchange;
    }

    public OrderItemBO setHasReturnExchange(Integer hasReturnExchange) {
        this.hasReturnExchange = hasReturnExchange;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderItemBO setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
