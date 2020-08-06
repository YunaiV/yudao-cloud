package cn.iocoder.mall.order.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 订单更新
 *
 * @author Sin
 * @time 2019-03-16 14:46
 */
@Data
@Accessors(chain = true)
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

}
