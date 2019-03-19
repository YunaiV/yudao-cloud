package cn.iocoder.mall.order.dataobject;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单
 *
 * @author Sin
 * @time 2019-03-16 13:49
 */
public class OrderDO implements Serializable {

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
     * 是否退货
     *
     * - 0、no
     * - 1、yes
     */
    private Integer hasReturn;
    /**
     * 是否换货
     *
     * - 0、no
     * - 1、yes
     */
    private Integer hasExchange;
    /**
     * 状态(如果有多个商品分开发货需要全部商品发完才会改变状态)
     *
     * - 0、代付款
     * - 1、已付款
     * - 2、已退款
     * - 3、已完成
     */
    private Integer status;
    /**
     * 删除状态
     *
     * - 0 未删除
     * - 1 已删除
     */
    private Integer deleteStatus;
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
                ", createTime=" + createTime +
                ", paymentTime=" + paymentTime +
                ", deliveryTime=" + deliveryTime +
                ", receiverTime=" + receiverTime +
                ", closingTime=" + closingTime +
                ", hasReturn=" + hasReturn +
                ", hasExchange=" + hasExchange +
                ", status=" + status +
                ", deleteStatus=" + deleteStatus +
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

    public Date getCreateTime() {
        return createTime;
    }

    public OrderDO setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getHasReturn() {
        return hasReturn;
    }

    public OrderDO setHasReturn(Integer hasReturn) {
        this.hasReturn = hasReturn;
        return this;
    }

    public Integer getHasExchange() {
        return hasExchange;
    }

    public OrderDO setHasExchange(Integer hasExchange) {
        this.hasExchange = hasExchange;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public OrderDO setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
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
