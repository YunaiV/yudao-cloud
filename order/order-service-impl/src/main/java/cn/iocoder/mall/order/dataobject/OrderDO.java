package cn.iocoder.mall.order.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

import java.util.Date;

/**
 * 订单
 *
 * @author Sin
 * @time 2019-03-16 13:49
 */
public class OrderDO extends BaseDO {

    /**
     * id
     */
    private Integer id;
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
        return "OrderDO{" +
                "id=" + id +
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

    public OrderDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderLogisticsId() {
        return orderLogisticsId;
    }

    public OrderDO setOrderLogisticsId(Integer orderLogisticsId) {
        this.orderLogisticsId = orderLogisticsId;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderDO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderDO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public OrderDO setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public OrderDO setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }

    public Date getReceiverTime() {
        return receiverTime;
    }

    public OrderDO setReceiverTime(Date receiverTime) {
        this.receiverTime = receiverTime;
        return this;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public OrderDO setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
        return this;
    }

    public Integer getHasReturnExchange() {
        return hasReturnExchange;
    }

    public OrderDO setHasReturnExchange(Integer hasReturnExchange) {
        this.hasReturnExchange = hasReturnExchange;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public OrderDO setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
