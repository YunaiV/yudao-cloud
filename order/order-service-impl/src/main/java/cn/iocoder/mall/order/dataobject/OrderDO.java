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
     * 编号
     */
    private Integer id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 交易金额
     */
    private Integer price;
    /**
     * 收件区域编号
     */
    private String receiverAreaNo;
    /**
     * 收件人名称
     */
    private String receiverName;
    /**
     * 收件手机号
     */
    private String receiverMobile;
    /**
     * 收件详细地址
     */
    private String receiverAddress;
    /**
     * 状态(如果有多个商品分开发货需要全部商品发完才会改变状态)
     *
     * - 0、代发货
     * - 1、已发货
     * - 2、已收货
     * - 20、换货中
     * - 21、换货成功
     * - 40、退货中
     * - 41、已退货
     */
    private Integer status;
    /**
     * 支付状态
     *
     * - 0、待支付
     * - 1、支付完成
     * - 2、已退款
     */
    private Integer payStatus;
    /**
     * 删除状态
     *
     * - 0 未删除
     * - 1 已删除
     */
    private Integer deleteStatus;
    /**
     * 订单创建时间
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
     * 成交时间
     */
    private Date closingTime;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "OrderDO{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", price=" + price +
                ", receiverAreaNo='" + receiverAreaNo + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverMobile='" + receiverMobile + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", status=" + status +
                ", payStatus=" + payStatus +
                ", deleteStatus=" + deleteStatus +
                ", createTime=" + createTime +
                ", paymentTime=" + paymentTime +
                ", deliveryTime=" + deliveryTime +
                ", closingTime=" + closingTime +
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

    public String getReceiverAreaNo() {
        return receiverAreaNo;
    }

    public OrderDO setReceiverAreaNo(String receiverAreaNo) {
        this.receiverAreaNo = receiverAreaNo;
        return this;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public OrderDO setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public OrderDO setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public OrderDO setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public OrderDO setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
        return this;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public OrderDO setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
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

    public Date getClosingTime() {
        return closingTime;
    }

    public OrderDO setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
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
