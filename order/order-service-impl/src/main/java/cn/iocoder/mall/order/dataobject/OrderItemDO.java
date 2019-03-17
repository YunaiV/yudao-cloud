package cn.iocoder.mall.order.dataobject;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单 item
 *
 * @author Sin
 * @time 2019-03-16 14:03
 */
public class OrderItemDO implements Serializable {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 商品编号
     */
    private String commodityId;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 金额(分)
     */
    private Integer price;
    /**
     * 状态
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
     * 发货时间
     */
    private Date deliveryTime;

    @Override
    public String toString() {
        return "OrderItemDO{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", status=" + status +
                ", deliveryTime=" + deliveryTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderItemDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderItemDO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public OrderItemDO setCommodityId(String commodityId) {
        this.commodityId = commodityId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItemDO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderItemDO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderItemDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public OrderItemDO setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }
}
