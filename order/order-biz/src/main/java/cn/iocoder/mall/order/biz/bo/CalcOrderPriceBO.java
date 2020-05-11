package cn.iocoder.mall.order.biz.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 计算订单价格结果 BO
 */
@Data
@Accessors(chain = true)
public class CalcOrderPriceBO {

//    /**
//     * 商品分组数组
//     */
//    private List<ItemGroup> itemGroups;
//    /**
//     * 优惠劵编号
//     */
//    private Integer couponCardId;
//    /**
//     * 优惠劵减少的金额
//     *
//     * 1. 若未使用优惠劵，返回 null
//     * 2. 该金额，已经分摊到每个 Item 的 discountTotal ，需要注意。
//     */
//    private Integer couponCardDiscountTotal;
//    /**
//     * 邮费信息
//     *
//     * TODO 芋艿，暂时未弄
//     */
//    private Postage postage;
//    /**
//     * 费用
//     */
//    private Fee fee;
//
//    /**
//     * 商品分组
//     *
//     * 多个商品，参加同一个活动，从而形成分组。
//     */
//    @Data
//    @Accessors(chain = true)
//    public static class ItemGroup {
//
//        /**
//         * 优惠活动
//         */
//        // TODO 芋艿，目前只会有【满减送】的情况，未来有新的促销方式，可能需要改成数组
//        private PromotionActivityBO activity;
//        /**
//         * 促销减少的金额
//         *
//         * 1. 若未参与促销活动，或不满足促销条件，返回 null
//         * 2. 该金额，已经分摊到每个 Item 的 discountTotal ，需要注意。
//         */
//        private Integer activityDiscountTotal;
//        /**
//         * 商品数组
//         */
//        private List<Item> items;
////        /**
////         * 费用
////         *
////         * TODO 芋艿，这里先偷懒，postageTotal 字段用不到。
////         */
////        private Fee fee; // 注释原因，不用这里了
//
//    }
//
//    @Data
//    @Accessors(chain = true)
//    public static class Item extends ProductSkuDetailBO { // TODO 芋艿，此处先偷懒继承
//
//        /**
//         * 是否选中
//         */
//        private Boolean selected;
//        /**
//         * 购买数量
//         */
//        private Integer buyQuantity;
//        /**
//         * 优惠活动
//         */
//        private PromotionActivityBO activity;
//        /**
//         * 原始单价，单位：分。
//         */
//        private Integer originPrice;
//        /**
//         * 购买单价，单位：分
//         */
//        private Integer buyPrice;
//        /**
//         * 最终价格，单位：分。
//         */
//        private Integer presentPrice;
//        /**
//         * 购买总金额，单位：分
//         *
//         * 用途类似 {@link #presentTotal}
//         */
//        private Integer buyTotal;
//        /**
//         * 优惠总金额，单位：分。
//         */
//        private Integer discountTotal;
//        /**
//         * 最终总金额，单位：分。
//         *
//         * 注意，presentPrice * quantity 不一定等于 presentTotal 。
//         * 因为，存在无法整除的情况。
//         * 举个例子，presentPrice = 8.33 ，quantity = 3 的情况，presentTotal 有可能是 24.99 ，也可能是 25 。
//         * 所以，需要存储一个该字段。
//         */
//        private Integer presentTotal;
//
//    }
//
//    /**
//     * 费用（合计）
//     */
//    @Data
//    @Accessors(chain = true)
//    public static class Fee {
//
//        /**
//         * 购买总价
//         */
//        private Integer buyTotal;
//        /**
//         * 优惠总价
//         *
//         * 注意，满多少元包邮，不算在优惠中。
//         */
//        private Integer discountTotal;
//        /**
//         * 邮费 TODO 芋艿，将  postage 改成 logistics
//         */
//        private Integer postageTotal;
//        /**
//         * 最终价格
//         *
//         * 计算公式 = 总价 - 优惠总价 + 邮费
//         */
//        private Integer presentTotal;
//
//        public Fee() {
//        }
//
//        public Fee(Integer buyTotal, Integer discountTotal, Integer postageTotal, Integer presentTotal) {
//            this.buyTotal = buyTotal;
//            this.discountTotal = discountTotal;
//            this.postageTotal = postageTotal;
//            this.presentTotal = presentTotal;
//        }
//    }
//
//    /**
//     * 邮费信息
//     */
//    @Data
//    @Accessors(chain = true)
//    public static class Postage {
//
//        /**
//         * 需要满足多少钱，可以包邮。单位：分
//         */
//        private Integer threshold;
//
//    }

}
