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
    private Integer skuId;
    /**
     * 数量
     */
    @NotNull
    @Max(value = 1000)
    private Integer quantity;

}
