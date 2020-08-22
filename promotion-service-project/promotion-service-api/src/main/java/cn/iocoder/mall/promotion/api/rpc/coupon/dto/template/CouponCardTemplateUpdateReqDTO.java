package cn.iocoder.mall.promotion.api.rpc.coupon.dto.template;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.enums.RangeTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 优惠劵模板更新 Request DTO
 */
@Data
@Accessors(chain = true)
public class CouponCardTemplateUpdateReqDTO implements Serializable {

    @NotNull(message = "编号不能为空")
    private Integer id;

    // ========== 基本信息 BEGIN ==========
    /**
     * 标题
     */
    @NotEmpty(message = "标题不能为空")
    @Length(min = 2, max = 16, message = "标题长度为 {min}-{max} 位")
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
     */
    @NotNull(message = "每人限领个数不能为空")
    @Min(value = 1, message = "每人限领个数最小为 {value}")
    private Integer quota;
    /**
     * 发放总量
     */
    @NotNull(message = "发放总量不能为空")
    @Min(value = 1, message = "每人限领个数最小为 {value}")
    private Integer total;
    // ========== 领取规则 END ==========

    // ========== 使用规则 BEGIN ==========
//    /**
//     * 是否设置满多少金额可用，单位：分
//     *
//     * 0-不限制
//     * 大于0-多少金额可用
//     */
//    @NotNull(message = "使用金额门槛不能为空")
//    @Min(value = 0L, message = "使用金额门槛最低为 {value}")
//    private Integer priceAvailable;
    /**
     * 可用范围的类型
     *
     * 10-全部（ALL）：所有可用
     * 20-部分（PART）：部分商品可用，或指定商品可用
     * 21-部分（PART）：部分商品不可用，或指定商品可用
     * 30-部分（PART）：部分分类可用，或指定分类可用
     * 31-部分（PART）：部分分类不可用，或指定分类可用
     */
    @NotNull(message = "可用范围的类型不能为空")
    @InEnum(value = RangeTypeEnum.class, message = "可用范围的类型必须在 {value}")
    private Integer rangeType;
    /**
     * 指定商品 / 分类列表，使用逗号分隔商品编号
     */
    private String rangeValues;
//    /**
//     * 生效日期类型
//     *
//     * 1-固定日期
//     * 2-领取日期：领到券 {@link #fixedEndTerm} 日开始 N 天内有效
//     */
//    @NotNull(message = "生效日期类型不能为空")
//    @InEnum(value = CouponTemplateDateTypeEnum.class, message = "生效日期类型必须在 {value}")
//    private Integer dateType;
//    /**
//     * 固定日期-生效开始时间
//     */
//    private Date validStartTime;
//    /**
//     * 固定日期-生效结束时间
//     */
//    private Date validEndTime;
//    /**
//     * 领取日期-开始天数
//     *
//     * 例如，0-当天；1-次天
//     */
//    @Min(value = 0L, message = "领取日期开始时间最小为 {value}")
//    private Integer fixedStartTerm;
//    /**
//     * 领取日期-结束天数
//     */
//    @Min(value = 1L, message = "领取日期结束时间最小为 {value}")
//    private Integer fixedEndTerm;
    // ========== 使用规则 END ==========

    // ========== 使用效果 BEGIN ==========
//    /**
//     * 优惠类型
//     *
//     * 1-代金卷
//     * 2-折扣卷
//     */
//    @NotNull(message = "优惠类型不能为空")
//    @InEnum(value = CouponTemplatePreferentialTypeEnum.class, message = "优惠类型必须在 {value}")
//    private Integer preferentialType;
//    /**
//     * 优惠金额，单位：分
//     */
//    @Min(value = 1, message = "优惠金额最小值为 {value}")
//    private Integer priceOff;
//    /**
//     * 折扣百分比。
//     *
//     * 例如，80% 为 80。
//     * 当 100% 为 100 ，则代表免费。
//     */
//    @Max(value = 100, message = "折扣比最大值为 {value}")
//    private Integer percentOff;
//    /**
//     * 折扣上限，仅在 {@link #preferentialType} 等于 2 时生效。
//     *
//     * 例如，折扣上限为 20 元，当使用 8 折优惠券，订单金额为 1000 元时，最高只可折扣 20 元，而非 80  元。
//     */
//    @Min(value = 1, message = "折扣上限最小值为 {value}")
//    private Integer discountPriceLimit;
    // ========== 使用效果 END ==========

}
