package cn.iocoder.mall.order.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

import java.util.Date;

/**
 * 订单 item
 *
 * @author Sin
 * @time 2019-03-16 14:03
 */
public class OrderItemDO extends BaseDO {

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
    private String skuId;
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

    public OrderItemDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderItemDO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderItemDO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getSkuId() {
        return skuId;
    }

    public OrderItemDO setSkuId(String skuId) {
        this.skuId = skuId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItemDO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderItemDO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public OrderItemDO setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public OrderItemDO setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }

    public Date getReceiverTime() {
        return receiverTime;
    }

    public OrderItemDO setReceiverTime(Date receiverTime) {
        this.receiverTime = receiverTime;
        return this;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public OrderItemDO setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
        return this;
    }

    public Integer getHasReturnExchange() {
        return hasReturnExchange;
    }

    public OrderItemDO setHasReturnExchange(Integer hasReturnExchange) {
        this.hasReturnExchange = hasReturnExchange;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderItemDO setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
