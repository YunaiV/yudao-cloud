package cn.iocoder.mall.order.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 计算订单价格 DTO
 */
@Data
@Accessors(chain = true)
public class CalcOrderPriceDTO {

    /**
     * 商品数组
     */
    private List<Item> items;

    @Data
    @Accessors(chain = true)
    public static class Item {

        /**
         * SKU 编号
         */
        private Integer skuId;
        /**
         * 数量
         */
        private Integer quantity;
        /**
         * 是否选中
         *
         * 注意下，目前只有在购物车的时候，才可能出现该属性为 false 。其它情况下，都会为 true 为主。
         */
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
