package cn.iocoder.mall.order.api.bo;

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

        // TODO 优惠活动
        private Object activity;
        /**
         * 商品数组
         */
        private List<Item> items;

    }

    @Data
    @Accessors(chain = true)
    public static class Item {

        // TODO 信息要相当完整


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
