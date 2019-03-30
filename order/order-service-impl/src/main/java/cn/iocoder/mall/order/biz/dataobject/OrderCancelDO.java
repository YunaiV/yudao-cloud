package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

import java.io.Serializable;

/**
 * 订单关闭
 *
 *  - 注意：订单只有在用户为付款前取消。
 *
 *  - 取消订单，这里是取消整个订单，不能对订单 item 单独做取消
 *
 * @author Sin
 * @time 2019-03-30 16:20
 */
public class OrderCancelDO extends BaseDO {

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
     * 关闭订单原因（字典）
     */
    private Integer reason;
    /**
     * 原因（如果选择其他，原因保存在这）
     */
    private String otherReason;

    @Override
    public String toString() {
        return "OrderCancelDO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", reason=" + reason +
                ", otherReason='" + otherReason + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderCancelDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderCancelDO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderCancelDO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getReason() {
        return reason;
    }

    public OrderCancelDO setReason(Integer reason) {
        this.reason = reason;
        return this;
    }

    public String getOtherReason() {
        return otherReason;
    }

    public OrderCancelDO setOtherReason(String otherReason) {
        this.otherReason = otherReason;
        return this;
    }
}
