package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

import java.util.Date;

/**
 * 订单 item
 *
 * @author Sin
 * @time 2019-03-16 14:03
 */
public class OrderItemDO extends DeletableDO {

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
     * 商品名称
     */
    private String skuName;
    /**
     * 商品图片
     */
    private String skuImage;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 价格(分)
     */
    private Integer price;
    /**
     * 支付金额（实付金额）
     */
    private Integer payAmount;
    /**
     * 物流id
     */
    private Integer orderLogisticsId;

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
                ", skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", skuImage='" + skuImage + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", payAmount=" + payAmount +
                ", orderLogisticsId=" + orderLogisticsId +
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

    public Integer getSkuId() {
        return skuId;
    }

    public OrderItemDO setSkuId(Integer skuId) {
        this.skuId = skuId;
        return this;
    }

    public String getSkuName() {
        return skuName;
    }

    public OrderItemDO setSkuName(String skuName) {
        this.skuName = skuName;
        return this;
    }

    public String getSkuImage() {
        return skuImage;
    }

    public OrderItemDO setSkuImage(String skuImage) {
        this.skuImage = skuImage;
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

    public Integer getPayAmount() {
        return payAmount;
    }

    public OrderItemDO setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
        return this;
    }

    public Integer getOrderLogisticsId() {
        return orderLogisticsId;
    }

    public OrderItemDO setOrderLogisticsId(Integer orderLogisticsId) {
        this.orderLogisticsId = orderLogisticsId;
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
