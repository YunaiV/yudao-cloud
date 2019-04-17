package cn.iocoder.mall.order.api.bo;

import cn.iocoder.mall.product.api.bo.ProductSkuDetailBO;
import cn.iocoder.mall.promotion.api.bo.PromotionActivityBO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 计算订单价格结果 BO
 */
@Data
@Accessors(chain = true)
public class CalcOrderPriceBO {

    /**
     * 商品分组数组
     */
    private List<ItemGroup> itemGroups;
    /**
     * 邮费信息
     */
    private Postage postage;
    /**
     * 费用
     */
    private Fee fee;

    /**
     * 商品分组
     *
     * 多个商品，参加同一个活动，从而形成分组。
     */
    @Data
    @Accessors(chain = true)
    public static class ItemGroup {

        /**
         * 优惠活动
         */
        // TODO 芋艿，目前只会有【满减送】的情况，未来有新的促销方式，可能需要改成数组
        private PromotionActivityBO activity;
        /**
         * 优惠活动是否生效
         *
         * 多个商品，参与某个活动，因为并发达到条件，所以会存在未生效的情况。所以一共有三种情况
         *
         * 1. activity 非空，activityEffectEffective 为 true，参与活动，且生效
         * 2. activity 非空，activityEffectEffective 为 false ，参与活动，并未生效
         * 3. activity 为空，activityEffectEffective 为空，并未参与活动。
         */
        private Boolean activityEffectEffective;
        /**
         * 商品数组
         */
        private List<Item> items;
        /**
         * 费用
         *
         * TODO 芋艿，这里先偷懒，postageTotal 字段用不到。
         */
        private Fee fee;

    }

    @Data
    @Accessors(chain = true)
    public static class Item extends ProductSkuDetailBO { // TODO 芋艿，此处先偷懒继承

        /**
         * 是否选中
         */
        private Boolean selected;
        /**
         * 购买数量
         */
        private Integer buyQuantity;
        /**
         * 优惠活动
         */
        private PromotionActivityBO activity;
        /**
         * 费用
         *
         * TODO 芋艿，这里先偷懒，postageTotal 字段用不到。
         */
        private Fee fee;
        /**
         * 折扣价
         */
        private Integer discountPrice;

    }

    /**
     * 费用（合计）
     */
    @Data
    @Accessors(chain = true)
    public static class Fee {

        /**
         * 总价
         */
        private Integer originalTotal;
        /**
         * 优惠总价
         *
         * 注意，满多少元包邮，不算在优惠中。
         */
        private Integer discountTotal;
        /**
         * 邮费
         */
        private Integer postageTotal;
        /**
         * 最终价格
         *
         * 计算公式 = 总价 - 优惠总价 + 邮费
         */
        private Integer presentTotal;

        public Fee() {
        }

        public Fee(Integer originalTotal, Integer discountTotal, Integer postageTotal, Integer presentTotal) {
            this.originalTotal = originalTotal;
            this.discountTotal = discountTotal;
            this.postageTotal = postageTotal;
            this.presentTotal = presentTotal;
        }
    }

    /**
     * 邮费信息
     */
    @Data
    @Accessors(chain = true)
    public static class Postage {

        /**
         * 需要满足多少钱，可以包邮。单位：分
         */
        private Integer threshold;

    }

}
