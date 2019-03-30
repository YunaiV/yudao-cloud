package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

import java.util.Date;

/**
 * 退货订单
 *
 * @author Sin
 * @time 2019-03-19 19:48
 */
public class OrderReturnDO extends DeletableDO {

    /**
     * 编号自动增长
     */
    private Integer id;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 订单号 （保存一个冗余）
     */
    private String orderNo;
    /**
     * 订单 item 编号
     */
    private Integer orderItemId;
    /**
     * 商品编号（保存一个冗余，如果一个订单下存在多个商品，会有很大的作用）
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
     * 物流id
     */
    private Integer orderLogisticsId;

    ///
    /// 退货原因

    /**
     * 退货原因(字典值)
     *
     * {@link cn.iocoder.mall.order.biz.constants.OrderReturnReasonEnum}
     */
    private Integer orderReason;
    /**
     * 原因（如果选择其他，原因保存在这）
     *
     * {@link cn.iocoder.mall.order.biz.constants.OrderReturnReasonEnum#REASON_000}
     */
    private String otherReasons;

    ///
    /// 时间信息

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 同意时间
     */
    private Date approvalTime;
    /**
     * 物流时间（填写物流单号时间）
     */
    private Date logisticsTime;
    /**
     * 收货时间
     */
    private Date receiverTime;
    /**
     * 成交时间（确认时间）
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
     * - 1、退货申请
     * - 2、申请成功
     * - 3、申请失败
     * - 4、退货中
     * - 5、退货成功
     */
    private Integer status;

    @Override
    public String toString() {
        return "OrderReturnDO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", orderItemId=" + orderItemId +
                ", skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", skuImage='" + skuImage + '\'' +
                ", orderLogisticsId=" + orderLogisticsId +
                ", orderReason=" + orderReason +
                ", otherReasons='" + otherReasons + '\'' +
                ", createTime=" + createTime +
                ", approvalTime=" + approvalTime +
                ", logisticsTime=" + logisticsTime +
                ", receiverTime=" + receiverTime +
                ", closingTime=" + closingTime +
                ", orderType=" + orderType +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderReturnDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderReturnDO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderReturnDO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public OrderReturnDO setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
        return this;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public OrderReturnDO setSkuId(Integer skuId) {
        this.skuId = skuId;
        return this;
    }

    public String getSkuName() {
        return skuName;
    }

    public OrderReturnDO setSkuName(String skuName) {
        this.skuName = skuName;
        return this;
    }

    public String getSkuImage() {
        return skuImage;
    }

    public OrderReturnDO setSkuImage(String skuImage) {
        this.skuImage = skuImage;
        return this;
    }

    public Integer getOrderLogisticsId() {
        return orderLogisticsId;
    }

    public OrderReturnDO setOrderLogisticsId(Integer orderLogisticsId) {
        this.orderLogisticsId = orderLogisticsId;
        return this;
    }

    public Integer getOrderReason() {
        return orderReason;
    }

    public OrderReturnDO setOrderReason(Integer orderReason) {
        this.orderReason = orderReason;
        return this;
    }

    public String getOtherReasons() {
        return otherReasons;
    }

    public OrderReturnDO setOtherReasons(String otherReasons) {
        this.otherReasons = otherReasons;
        return this;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public OrderReturnDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public OrderReturnDO setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
        return this;
    }

    public Date getLogisticsTime() {
        return logisticsTime;
    }

    public OrderReturnDO setLogisticsTime(Date logisticsTime) {
        this.logisticsTime = logisticsTime;
        return this;
    }

    public Date getReceiverTime() {
        return receiverTime;
    }

    public OrderReturnDO setReceiverTime(Date receiverTime) {
        this.receiverTime = receiverTime;
        return this;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public OrderReturnDO setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
        return this;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public OrderReturnDO setOrderType(Integer orderType) {
        this.orderType = orderType;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderReturnDO setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
