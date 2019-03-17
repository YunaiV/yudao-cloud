package cn.iocoder.mall.order.api.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author Sin
 * @time 2019-03-17 09:37
 */
public class OrderCreateItemDTO {

    /**
     * 商品编号
     */
    @NotNull
    private String commodityId;
    /**
     * 数量
     */
    @NotNull
    @Max(value = 1000)
    private Integer quantity;

    @Override
    public String toString() {
        return "OrderCreateItemDTO{" +
                "commodityId='" + commodityId + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public String getCommodityId() {
        return commodityId;
    }

    public OrderCreateItemDTO setCommodityId(String commodityId) {
        this.commodityId = commodityId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderCreateItemDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
