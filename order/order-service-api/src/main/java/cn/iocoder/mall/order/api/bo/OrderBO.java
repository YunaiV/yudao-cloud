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
     * 订单金额
     */
    private Integer money;

    @Override
    public String toString() {
        return "OrderBO{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", money=" + money +
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

    public Integer getMoney() {
        return money;
    }

    public OrderBO setMoney(Integer money) {
        this.money = money;
        return this;
    }
}
