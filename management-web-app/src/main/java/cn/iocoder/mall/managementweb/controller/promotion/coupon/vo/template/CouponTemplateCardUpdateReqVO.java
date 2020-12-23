package cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.enums.RangeTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("优惠劵模板更新 Request VO")
@Data
@Accessors(chain = true)
public class CouponTemplateCardUpdateReqVO {

    // ========== 基本信息 BEGIN ==========
    @ApiModelProperty(value = "模板编号，自增唯一", required = true, example = "1")
    @NotNull(message = "编号不能为空")
    private Integer id;
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
    @NotNull(message = "可用范围的类型不能为空")
    @InEnum(value = RangeTypeEnum.class, message = "可用范围的类型必须在 {value}")
    private Integer rangeType;
    @ApiModelProperty(value = "指定商品 / 分类列表，使用逗号分隔商品编号", example = "1,3,5")
    private String rangeValues;
    // ========== 使用规则 END ==========

}
