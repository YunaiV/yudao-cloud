package cn.iocoder.mall.order.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 购物车明细 BO
 */
@Data
@Accessors(chain = true)
public class CartBO {

    /**
     * 商品分组数组
     */
    private List<CartItemGroupBO> itemGroups;
    /**
     * 费用
     */
    private FeeMessageBO fee;

}
