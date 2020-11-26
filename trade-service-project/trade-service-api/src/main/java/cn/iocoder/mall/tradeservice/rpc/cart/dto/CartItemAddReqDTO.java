package cn.iocoder.mall.tradeservice.rpc.cart.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 购物车添加购物项 Request DTO
 */
@Data
@Accessors(chain = true)
public class CartItemAddReqDTO implements Serializable {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    /**
     * 商品 SKU 编号
     */
    @NotNull(message = "商品 SKU 编号不能为空")
    private Integer skuId;
    /**
     * 数量
     */
    @NotNull(message = "数量不能为空")
    @Min(message = "数量必须大于 0", value = 1L)
    private Integer quantity;

}
