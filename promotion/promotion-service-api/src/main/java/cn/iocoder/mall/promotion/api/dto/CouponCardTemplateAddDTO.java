package cn.iocoder.mall.promotion.api.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CouponCardTemplateAddDTO {

    // ========== 基本信息 BEGIN ==========
    /**
     * 标题
     */
    @NotEmpty(message = "标题不能为空")
    @Length(min = 6, max = 16, message = "标题长度为 {min}-{max} 位")
    private String title;
    /**
     * 使用说明
     */
    @Length(max = 255, message = "使用说明最大长度为 {max} 位")
    private String description;
    // ========== 基本信息 END ==========

    // ========== 领取规则 BEGIN ==========
    /**
     * 每人限领个数
     *
     * null - 则表示不限制
     */
    private Integer quota;
    /**
     * 剩余可用库存
     *
     * null - 则表示无限库存
     */
    private Integer stock;
    // ========== 领取规则 END ==========

    // ========== 使用规则 BEGIN ==========
    /**
     * 是否设置满多少金额可用，单位：分
     *
     * 0-不限制
     * 大于0-多少金额可用
     */
    @NotNull(message = "使用金额门槛不能为空")
    private Integer priceAvailable;
    /**
     * 可用范围的类型
     *
     * 10-全部（ALL）：所有可用
     * 20-部分（PART）：部分商品可用，或指定商品可用
     * 21-部分（PART）：部分商品不可用，或指定商品可用
     * 30-部分（PART）：部分分类可用，或指定商品可用
     * 31-部分（PART）：部分分类不可用，或指定商品可用
     */
    @NotNull(message = "可用范围的类型不能为空")
    private Integer rangeType;
    /**
     * 指定商品 / 分类列表，使用逗号分隔商品编号
     */
    private String rangeValues;
    /**
     * 生效日期类型
     *
     * 1-固定日期
     * 2-领取日期：领到券 {@link #fixedTerm} 日开始 N 天内有效
     */
    @NotNull(message = "生效日期类型不能为空")
    private Integer dateType;
    /**
     * 固定日期-生效开始时间
     */
    private Date validStartTime;
    /**
     * 固定日期-生效结束时间
     */
    private Date validEndTime;
//    /**
//     * 领取日期-开始天数
//     *
//     * 例如，0-当天；1-次天
//     */
//    private Integer fixedBeginTerm;
    /**
     * 领取日期-结束天数
     */
    private Integer fixedTerm;
    // ========== 使用规则 END ==========

    // ========== 使用效果 BEGIN ==========
    /**
     * 优惠类型
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
    /**
     * 优惠金额，单位：分
     */
    private Integer priceOff;
    /**
     * 折扣上限，仅在 {@link #preferentialType} 等于 2 时生效。
     *
     * 例如，折扣上限为 20 元，当使用 8 折优惠券，订单金额为 1000 元时，最高只可折扣 20 元，而非 80  元。
     */
    private Integer discountPriceLimit;
    // ========== 使用效果 END ==========

}
