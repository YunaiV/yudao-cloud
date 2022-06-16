package cn.iocoder.mall.tradeservice.service.cart.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 购物车的商品信息查询 BO
 */
@Data
@Accessors(chain = true)
public class CartItemListQueryBO {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    /**
     * 是否选中
     */
    private Boolean selected;

}
