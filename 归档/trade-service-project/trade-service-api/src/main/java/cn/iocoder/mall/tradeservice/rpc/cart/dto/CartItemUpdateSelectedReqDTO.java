package cn.iocoder.mall.tradeservice.rpc.cart.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

/**
 * 购物车更新是否选中 Request DTO
 */
@Data
@Accessors(chain = true)
public class CartItemUpdateSelectedReqDTO implements Serializable {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    /**
     * 商品 SKU 编号列表
     */
    @NotNull(message = "商品 SKU 编号列表不能为空")
    private Collection<Integer> skuIds;
    /**
     * 是否选中
     */
    @NotNull(message = "是否选中不能为空")
    private Boolean selected;

}
