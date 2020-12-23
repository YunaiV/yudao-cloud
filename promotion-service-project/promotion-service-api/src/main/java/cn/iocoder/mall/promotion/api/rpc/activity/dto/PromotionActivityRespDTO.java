package cn.iocoder.mall.promotion.api.rpc.activity.dto;

import cn.iocoder.mall.promotion.api.enums.activity.PromotionActivityStatusEnum;
import cn.iocoder.mall.promotion.api.enums.activity.PromotionActivityTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 促销活动 Response DTO
 */
@Data
@Accessors(chain = true)
public class PromotionActivityRespDTO implements Serializable {

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
     * 参见 {@link PromotionActivityTypeEnum} 枚举
     */
    private Integer activityType;
    /**
     * 活动状态
     *
     * 参见 {@link PromotionActivityStatusEnum} 枚举
     */
    private Integer status;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 限制折扣
     */
    private TimeLimitedDiscount timeLimitedDiscount;
    /**
     * 满减送
     */
    private FullPrivilege fullPrivilege;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 限时折扣
     */
    @Data
    @Accessors(chain = true)
    public static class TimeLimitedDiscount implements Serializable {

        /**
         * 商品折扣
         */
        @Data
        @Accessors(chain = true)
        public static class Item implements Serializable {

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
    public static class FullPrivilege implements Serializable {

        /**
         * 优惠
         */
        @Data
        @Accessors(chain = true)
        public static class Privilege implements Serializable {

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
         * 参见 {@link cn.iocoder.mall.promotion.api.enums.RangeTypeEnum} 枚举
         * 暂时只用 “所有可用” + “PRODUCT_INCLUDE_PRT”
         */
        private Integer rangeType;
        /**
         * 指定可用商品列表
         */
        private List<Integer> rangeValues;
        /**
         * 是否循环
         */
        private Boolean cycled;
        /**
         * 优惠数组
         */
        private List<Privilege> privileges;

    }

}
