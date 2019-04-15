package cn.iocoder.mall.promotion.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true)
public class PromotionActivityBO {

    /**
     * 活动编号
     */
    private Integer id;
    /**
     * 活动标题
     */
    private String title;
    /**
     * 活动类型
     *
     * 参见 {@link cn.iocoder.mall.promotion.api.constant.PromotionActivityTypeEnum} 枚举
     */
    private Integer type;
    /**
     * 活动状态
     *
     * 参见 {@link cn.iocoder.mall.promotion.api.constant.PromotionActivityStatusEnum} 枚举
     */
    private Integer status;
    /**
     * 匹配的商品 SPU 编号
     */
    private Set<Integer> spuIds;

    /**
     * 限制折扣
     */
    @Data
    @Accessors(chain = true)
    public static class TimeLimitedDiscount {

        /**
         * 商品折扣
         */
        @Data
        @Accessors(chain = true)
        public static class Item {

            /**
             * 商品 SPU 编号
             */
            private Integer spuId;
            /**
             * 优惠类型
             */
            private Integer preferentialType;
            /**
             * 优惠值
             */
            private Integer preferentialValue;

        }

        /**
         * 每人每种限购多少
         *
         * 当 quota = 0 时，表示不限购
         */
        private Integer quota;
        /**
         * 商品折扣数组
         */
        private List<Item> items;

    }

    /**
     * 满减送
     */
    @Data
    @Accessors(chain = true)
    public static class FullPrivilege {

        /**
         * 优惠
         */
        @Data
        @Accessors(chain = true)
        public static class Privilege {

            /**
             * 满足类型
             *
             * 1 - 金额
             * 2 - 件数
             */
            private Integer meetType;
            /**
             * 满足值
             */
            private Integer meetValue;
            /**
             * 优惠类型
             */
            private Integer preferentialType;
            /**
             * 优惠值
             */
            private Integer preferentialValue;

        }

        /**
         * 可用范围的类型
         *
         * 参见 {@link cn.iocoder.mall.promotion.api.constant.RangeTypeEnum} 枚举
         * 暂时只用 “所有可用” + “PRODUCT_INCLUDE_PRT”
         */
        private Integer rangeType;
        /**
         * 指定可用商品列表
         */
        private List<Integer> rangeValues;
        /**
         * 优惠数组
         */
        private List<Privilege> privileges;

    }

}
