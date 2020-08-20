package cn.iocoder.mall.promotionservice.dal.mysql.dataobject.activity;

import cn.iocoder.mall.mybatis.core.dataobject.BaseDO;
import cn.iocoder.mall.promotion.api.enums.activity.PromotionActivityStatusEnum;
import cn.iocoder.mall.promotion.api.enums.activity.PromotionActivityTypeEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 促销活动 DO
 */
@TableName(value = "promotion_activity", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PromotionActivityDO extends BaseDO {

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
//    /**
//     * 促销类型
//     * // TODO 芋艿 https://jos.jd.com/api/complexTemplate.htm?webPamer=promotion_v_o&groupName=%E4%BF%83%E9%94%80API&id=54&restName=jingdong.seller.promotion.list&isMulti=false 促销类型，可选值：单品促销（1），赠品促销（4），套装促销（6），总价促销（10）
//     */
//    private Integer promotionType;
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
     * 失效时间
     */
    private Date invalidTime;
    /**
     * 删除时间
     */
    private Date deleteTime;
    /**
     * 限制折扣字符串，使用 JSON 序列化成字符串存储
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private TimeLimitedDiscount timeLimitedDiscount;
    /**
     * 满减送字符串，使用 JSON 序列化成字符串存储
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private FullPrivilege fullPrivilege;

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
//            /**
//             * 是否包邮
//             */
//            private Boolean isPostage;
//            /**
//             * 积分
//             */
//            private Integer score;
//            /**
//             * 优惠劵（码）分组编号
//             */
//            private Integer couponTemplateId;
//            /**
//             * 优惠劵（码）数量
//             */
//            private Integer couponNum;
//            /**
//             * 赠品编号
//             */
//            private Integer presentId;

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
