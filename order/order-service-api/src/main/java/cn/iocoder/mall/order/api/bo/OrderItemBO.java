package cn.iocoder.mall.order.api.bo;

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
     * 发货方式
     *
     * - 1 未选择
     * - 2 在线下单
     * - 3 自己联系快递
     * - 4 无物流
     */
    private Integer deliveryType;
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

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除状态
     */
    private Integer deleted;

    @Override
    public String toString() {
        return "OrderItemBO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", skuImage='" + skuImage + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", payAmount=" + payAmount +
                ", paymentTime=" + paymentTime +
                ", deliveryTime=" + deliveryTime +
                ", receiverTime=" + receiverTime +
                ", closingTime=" + closingTime +
                ", hasReturnExchange=" + hasReturnExchange +
                ", deliveryType=" + deliveryType +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
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

    public String getSkuName() {
        return skuName;
    }

    public OrderItemBO setSkuName(String skuName) {
        this.skuName = skuName;
        return this;
    }

    public String getSkuImage() {
        return skuImage;
    }

    public OrderItemBO setSkuImage(String skuImage) {
        this.skuImage = skuImage;
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

    public Integer getPayAmount() {
        return payAmount;
    }

    public OrderItemBO setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
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

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public OrderItemBO setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderItemBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OrderItemBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public OrderItemBO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public OrderItemBO setDeleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }
}
