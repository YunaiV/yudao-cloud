package cn.iocoder.mall.order.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单退货 - 创建
 *
 * @author Sin
 * @time 2019-03-30 15:34
 */
public class OrderReturnCreateDTO implements Serializable {

    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 订单 item 编号
     */
    private Integer orderItemId;
    /**
     * 退货原因(字典值)
     */
    private Integer orderReason;
    /**
     * 原因（如果选择其他，原因保存在这）
     */
    private String otherReasons;
    /**
     * 订单类型
     *
     * - 0、为 Order 订单 （对整个订单退货）
     * - 1、为 OrderItem 订单 （对订单某一个商品退货）
     */
    private Integer orderType;

    @Override
    public String toString() {
        return "OrderReturnCreateDTO{" +
                "orderId=" + orderId +
                ", orderItemId=" + orderItemId +
                ", orderReason=" + orderReason +
                ", otherReasons='" + otherReasons + '\'' +
                ", orderType=" + orderType +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderReturnCreateDTO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public OrderReturnCreateDTO setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
        return this;
    }

    public Integer getOrderReason() {
        return orderReason;
    }

    public OrderReturnCreateDTO setOrderReason(Integer orderReason) {
        this.orderReason = orderReason;
        return this;
    }

    public String getOtherReasons() {
        return otherReasons;
    }

    public OrderReturnCreateDTO setOtherReasons(String otherReasons) {
        this.otherReasons = otherReasons;
        return this;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public OrderReturnCreateDTO setOrderType(Integer orderType) {
        this.orderType = orderType;
        return this;
    }
}
