package cn.iocoder.mall.order.api.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sin
 * @time 2019-03-23 10:22
 */
public class OrderItemDeletedDTO implements Serializable {

    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单item id
     */
    private List<Integer> orderItemIds;

    @Override
    public String toString() {
        return "OrderItemDeletedDTO{" +
                "orderId=" + orderId +
                ", orderItemIds=" + orderItemIds +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderItemDeletedDTO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public List<Integer> getOrderItemIds() {
        return orderItemIds;
    }

    public OrderItemDeletedDTO setOrderItemIds(List<Integer> orderItemIds) {
        this.orderItemIds = orderItemIds;
        return this;
    }
}
