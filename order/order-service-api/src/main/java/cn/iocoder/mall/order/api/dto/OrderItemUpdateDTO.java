package cn.iocoder.mall.order.api.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 订单更新
 *
 * @author Sin
 * @time 2019-03-16 14:46
 */
public class OrderItemUpdateDTO implements Serializable {

    /**
     * 编号
     */
    @NotNull
    private Integer id;
    /**
     * 商品编号
     */
    @NotNull
    private Integer skuId;
    /**
     * 数量
     */
    @NotNull
    private Integer quantity;
    /**
     * 金额(分)
     */
    @NotNull
    private Integer price;

    @Override
    public String toString() {
        return "OrderItemUpdateDTO{" +
                "id=" + id +
                ", skuId=" + skuId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderItemUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public OrderItemUpdateDTO setSkuId(Integer skuId) {
        this.skuId = skuId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItemUpdateDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderItemUpdateDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
