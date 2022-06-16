package cn.iocoder.mall.tradeservice.rpc.cart.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 购物车删除商品列表 Request DTO
 */
@Data
@Accessors(chain = true)
public class CartItemDeleteListReqDTO implements Serializable {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    /**
     * 商品 SKU 编号列表
     */
    @NotNull(message = "商品 SKU 编号列表不能为空")
    private List<Integer> skuIds;

}
