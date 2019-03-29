package cn.iocoder.mall.order.api.bo;

import java.io.Serializable;

/**
 * 订单创建 BO
 *
 * @author Sin
 * @time 2019-03-16 14:38
 */
public class OrderCreateBO implements Serializable {

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
    private Integer payAmount;

    @Override
    public String toString() {
        return "OrderCreateBO{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", payAmount=" + payAmount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderCreateBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderCreateBO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public OrderCreateBO setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
        return this;
    }
}
