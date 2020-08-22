package cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.enums.PreferentialTypeEnum;
import cn.iocoder.mall.promotion.api.enums.RangeTypeEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateDateTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("优惠劵模板创建 Request VO")
@Data
@Accessors(chain = true)
public class CouponTemplateCardCreateReqVO {

    // ========== 基本信息 BEGIN ==========
    @ApiModelProperty(value = "标题", required = true, example = "优惠劵牛逼")
    @NotEmpty(message = "标题不能为空")
    @Length(min = 2, max = 16, message = "标题长度为 {min}-{max} 位")
    private String title;
    @ApiModelProperty(value = "使用说明", required = true, example = "我只是描述")
    @Length(max = 255, message = "使用说明最大长度为 {max} 位")
    private String description;
    @ApiModelProperty(value = "每人限领个数", example = "1", notes = "null - 则表示不限制")
    @Min(value = 1, message = "每人限领个数最小为 {value}")
    private Integer quota;
    @ApiModelProperty(value = "发放总量", example = "100")
    @Min(value = 1, message = "每人限领个数最小为 {value}")
    private Integer total;
    // ========== 领取规则 END ==========

    // ========== 使用规则 BEGIN ==========
    @ApiModelProperty(value = "是否设置满多少金额可用，单位：分", required = true, example = "0", notes = "0-不限制；大于0-多少金额可用")
    @Min(value = 0L, message = "使用金额门槛最低为 {value}")
    private Integer priceAvailable;
    @ApiModelProperty(value = "可用范围的类型", required = true, example = "10", notes = "参见 RangeTypeEnum 枚举")
    @NotNull(message = "可用范围的类型不能为空")
    @InEnum(value = RangeTypeEnum.class, message = "可用范围的类型必须在 {value}")
    private Integer rangeType;
    @ApiModelProperty(value = "指定商品 / 分类列表，使用逗号分隔商品编号", example = "1,3,5")
    private String rangeValues;
    @ApiModelProperty(value = "生效日期类型", example = "1", notes = "参见 CouponTemplateDateTypeEnum 枚举")
    @NotNull(message = "生效日期类型不能为空")
    @InEnum(value = CouponTemplateDateTypeEnum.class, message = "生效日期类型必须在 {value}")
    private Integer dateType;
    @ApiModelProperty(value = "固定日期-生效开始时间", notes = "当 dateType 为固定日期时，非空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validStartTime;
    @ApiModelProperty(value = "固定日期-生效结束时间", notes = "当 dateType 为固定日期时，非空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validEndTime;
    @ApiModelProperty(value = "领取日期-开始天数", example = "0", notes = "例如，0-当天；1-次天")
    @Min(value = 0L, message = "领取日期开始时间最小为 {value}")
    private Integer fixedStartTerm;
    @ApiModelProperty(value = "领取日期-结束天数", example = "1", notes = "当 dateType 为领取日期时，非空")
    @Min(value = 1L, message = "领取日期结束时间最小为 {value}")
    private Integer fixedEndTerm;

    // ========== 使用效果 BEGIN ==========
    @ApiModelProperty(value = "优惠类型", required = true, example = "1", notes = "参见 PreferentialTypeEnum 枚举")
    @NotNull(message = "优惠类型不能为空")
    @InEnum(value = PreferentialTypeEnum.class, message = "优惠类型必须在 {value}")
    private Integer preferentialType;
    @ApiModelProperty(value = "折扣百分比", example = "80", notes = "当 preferentialType 为现金券时，非空")
    @Max(value = 100, message = "折扣比最大值为 {value}")
    private Integer percentOff;
    @ApiModelProperty(value = "优惠金额，单位：分", example = "100", notes = "当 preferentialType 为折扣卷时，非空")
    @Min(value = 1, message = "优惠金额最小值为 {value}")
    private Integer priceOff;
    @ApiModelProperty(value = "折扣上限", example = "100", notes = "当 preferentialType 为折扣卷时，非空")
    @Min(value = 1, message = "折扣上限最小值为 {value}")
    private Integer discountPriceLimit;
    // ========== 使用效果 END ==========

}
