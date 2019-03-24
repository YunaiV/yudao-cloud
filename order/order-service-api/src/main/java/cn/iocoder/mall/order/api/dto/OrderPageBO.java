package cn.iocoder.mall.order.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单 page
 *
 * @author Sin
 * @time 2019-03-23 14:30
 */
public class OrderPageBO implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 物流id
     */
    private Integer orderLogisticsId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 交易金额
     */
    private Integer price;

    ///
    /// 时间信息

    /**
     * 付款时间（待发货）
     */
    private Date paymentTime;
    /**
     * 发货时间（待收货）
     */
    private Date deliveryTime;
    /**
     * 收货时间（已签收）
     */
    private Date receiverTime;
    /**
     * 成交时间（用户确认收货 -> status = 已完成）
     */
    private Date closingTime;

    ///
    /// 其他

    /**
     * 是否退货
     *
     * - 0、没有
     * - 1、换货
     * - 2、退货
     * - 3、换货 + 退货
     */
    private Integer hasReturnExchange;
    /**
     * 状态(如果有多个商品分开发货需要全部商品发完才会改变状态)
     *
     * - 0、待付款
     * - 1、待发货
     * - 2、待收获
     * - 3、已完成
     * - 4、已关闭
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "OrderPageBO{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderLogisticsId=" + orderLogisticsId +
                ", orderNo='" + orderNo + '\'' +
                ", price=" + price +
                ", paymentTime=" + paymentTime +
                ", deliveryTime=" + deliveryTime +
                ", receiverTime=" + receiverTime +
                ", closingTime=" + closingTime +
                ", hasReturnExchange=" + hasReturnExchange +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderPageBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public OrderPageBO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getOrderLogisticsId() {
        return orderLogisticsId;
    }

    public OrderPageBO setOrderLogisticsId(Integer orderLogisticsId) {
        this.orderLogisticsId = orderLogisticsId;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderPageBO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderPageBO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public OrderPageBO setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public OrderPageBO setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }

    public Date getReceiverTime() {
        return receiverTime;
    }

    public OrderPageBO setReceiverTime(Date receiverTime) {
        this.receiverTime = receiverTime;
        return this;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public OrderPageBO setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
        return this;
    }

    public Integer getHasReturnExchange() {
        return hasReturnExchange;
    }

    public OrderPageBO setHasReturnExchange(Integer hasReturnExchange) {
        this.hasReturnExchange = hasReturnExchange;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderPageBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public OrderPageBO setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
