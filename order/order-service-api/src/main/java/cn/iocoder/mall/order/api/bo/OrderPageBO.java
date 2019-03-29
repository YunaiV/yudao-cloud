package cn.iocoder.mall.order.api.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 订单分页
 *
 * @author Sin
 * @time 2019-03-27 21:27
 */
public class OrderPageBO implements Serializable {

    /**
     * 总条数
     */
    private Integer total;
    /**
     * 订单列表
     */
    private List<OrderBO> orders;

    @Override
    public String toString() {
        return "OrderPageBO{" +
                "total=" + total +
                ", orders=" + orders +
                '}';
    }

    public Integer getTotal() {
        return total;
    }

    public OrderPageBO setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public List<OrderBO> getOrders() {
        return orders;
    }

    public OrderPageBO setOrders(List<OrderBO> orders) {
        this.orders = orders;
        return this;
    }
}
