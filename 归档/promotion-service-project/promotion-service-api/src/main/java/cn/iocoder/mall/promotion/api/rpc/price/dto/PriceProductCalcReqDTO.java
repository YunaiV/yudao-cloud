package cn.iocoder.mall.promotion.api.rpc.price.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 商品价格计算 Request DTO
 */
@Data
@Accessors(chain = true)
public class PriceProductCalcReqDTO implements Serializable {

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 优惠劵编号
     */
    private Integer couponCardId;

    /**
     * 商品 SKU 数组
     */
    @NotNull(message = "商品数组不能为空")
    private List<Item> items;

    /**
     * 商品 SKU
     */
    @Data
    @Accessors(chain = true)
    public static class Item implements Serializable {

        /**
         * SKU 编号
         */
        @NotNull(message = "商品 SKU 编号不能为空")
        private Integer skuId;
        /**
         * 数量
         */
        @NotNull(message = "商品 SKU 数量不能为空")
        private Integer quantity;
        /**
         * 是否选中
         */
        @NotNull(message = "是否选中不能为空")
        private Boolean selected;

        public Item() {
        }

        public Item(Integer skuId, Integer quantity, Boolean selected) {
            this.skuId = skuId;
            this.quantity = quantity;
            this.selected = selected;
        }

    }

}
