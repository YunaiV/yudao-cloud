package cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon;

import cn.iocoder.mall.mybatis.core.dataobject.BaseDO;
import cn.iocoder.mall.promotion.api.enums.PreferentialTypeEnum;
import cn.iocoder.mall.promotion.api.enums.RangeTypeEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateDateTypeEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateStatusEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateTypeEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 优惠劵（码）模板 DO
 *
 * 当用户领取时，会生成 {@link CouponCardDO} 优惠劵（码）。
 */
@TableName("coupon_template")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CouponTemplateDO extends BaseDO {

    // ========== 基本信息 BEGIN ==========
    /**
     * 模板编号，自增唯一。
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 使用说明
     */
    private String description;
    /**
     * 类型
     *
     * 枚举 {@link CouponTemplateTypeEnum}
     *
     * 1-优惠劵
     * 2-优惠码
     */
    private Integer type;
    /**
     * 优惠码状态
     *
     * 枚举 {@link CouponTemplateStatusEnum}
     *
     * 当优惠劵（码）开启中，可以手动操作，设置禁用中。
     */
    private Integer status;
//    /**
//     * 是否可分享领取链接
//     */
//    private Boolean isShare;
//    /**
//     * 设置为失效时间
//     */
//    private Date invalidTime;
//    /**
//     * 删除时间
//     */
//    private Date deleteTime;

    // ========== 基本信息 END ==========

    // ========== 领取规则 BEGIN ==========
//    /**
//     * 是否限制领用者的等级
//     *
//     * 0-不限制
//     * 大于0-领用者必须是这个等级编号
//     *
//     * 【优惠劵独有】
//     */
//    private Integer needUserLevel;
    /**
     * 每人限领个数
     *
     * null - 则表示不限制
     */
    private Integer quota;
    /**
     * 发行总量
     */
    private Integer total;
    // ========== 领取规则 END ==========

    // ========== 使用规则 BEGIN ==========
//    /**
//     * 是否仅原价购买商品时可用
//     *
//     * true-是
//     * false-否
//     */
//    private Boolean isForbidPreference;
    /**
     * 是否设置满多少金额可用，单位：分
     *
     * 0-不限制
     * 大于0-多少金额可用
     */
    private Integer priceAvailable;
    /**
     * 可用范围的类型
     *
     * 枚举 {@link RangeTypeEnum}
     *
     * 10-全部（ALL）：所有可用
     * 20-部分（PART）：部分商品可用，或指定商品可用
     * 21-部分（PART）：部分商品不可用，或指定商品可用
     * 30-部分（PART）：部分分类可用，或指定商品可用
     * 31-部分（PART）：部分分类不可用，或指定商品可用
     */
    private Integer rangeType;
    /**
     * 指定商品 / 分类列表，使用逗号分隔商品编号
     */
    private String rangeValues;
    /**
     * 生效日期类型
     *
     * 枚举 {@link CouponTemplateDateTypeEnum}
     *
     * 1-固定日期
     * 2-领取日期：领到券 {@link #fixedStartTerm} 日开始 N 天内有效
     */
    private Integer dateType;
    /**
     * 固定日期-生效开始时间
     */
    private Date validStartTime;
    /**
     * 固定日期-生效结束时间
     */
    private Date validEndTime;
    /**
     * 领取日期-开始天数
     *
     * 例如，0-当天；1-次天
     */
    private Integer fixedStartTerm;
    /**
     * 领取日期-结束天数
     */
    private Integer fixedEndTerm;
//    /**
//     * 是否到期前4天发送提醒
//     *
//     * true-发送
//     * false-不发送
//     */
//    private Boolean expireNotice;
    // ========== 使用规则 END ==========

    // ========== 使用效果 BEGIN ==========
    /**
     * 优惠类型
     *
     * 枚举 {@link PreferentialTypeEnum}
     *
     * 1-代金卷
     * 2-折扣卷
     */
    private Integer preferentialType;
    /**
     * 折扣百分比。
     *
     * 例如，80% 为 80。
     * 当 100% 为 100 ，则代表免费。
     */
    private Integer percentOff;
//    /**
//     * 是否是随机优惠券
//     *
//     * true-随机
//     * false-不随机
//     *
//     * 【优惠劵独有】
//     */
//    private Boolean isRandom;
    /**
     * 优惠金额，单位：分
     */
//  * 当 {@link #isRandom} 为 true 时，代表随机优惠金额的下限
    private Integer priceOff;
//    /**
//     * 优惠金额上限
//     *
//     * 【优惠劵独有】
//     */
//    private Integer valueRandomTo;
    /**
     * 折扣上限，仅在 {@link #preferentialType} 等于 2 时生效。
     *
     * 例如，折扣上限为 20 元，当使用 8 折优惠券，订单金额为 1000 元时，最高只可折扣 20 元，而非 80  元。
     */
    private Integer discountPriceLimit;
    // ========== 使用效果 END ==========

    // ========== 统计信息 BEGIN ==========
//    /**
//     * 领取优惠券的人数
//     */
//    private Integer statFetchUserNum;
    /**
     * 领取优惠券的次数
     */
    private Integer statFetchNum;
//    /**
//     * 使用优惠券的次数
//     */
//    private Integer statUseNum;
    // ========== 统计信息 END ==========

    // ========== 优惠码 BEGIN ==========
    /**
     * 码类型
     *
     * 1-一卡一码（UNIQUE）
     * 2-通用码（GENERAL）
     *
     * 【优惠码独有】 @see CouponCodeDO
     */
    private Integer codeType;
    /**
     * 通用码
     */
    private String commonCode;
    // ========== 优惠码 BEGIN ==========

}
