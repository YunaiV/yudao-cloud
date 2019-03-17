package cn.iocoder.mall.order.api.bo;

import java.io.Serializable;

/**
 * 订单创建 BO
 *
 * @author Sin
 * @time 2019-03-16 14:38
 */
public class OrderBO implements Serializable {

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

    @Override
    public String toString() {
        return "OrderBO{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", price=" + price +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderBO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderBO setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
