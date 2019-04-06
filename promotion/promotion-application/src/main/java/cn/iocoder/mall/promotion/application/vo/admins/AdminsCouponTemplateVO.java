package cn.iocoder.mall.promotion.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("CouponTemplate VO")
@Data
@Accessors(chain = true)
public class AdminsCouponTemplateVO {

    // ========== 基本信息 BEGIN ==========
    @ApiModelProperty(value = "模板编号，自增唯一", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "标题", required = true, example = "优惠劵牛逼")
    private String title;
    @ApiModelProperty(value = "使用说明", required = true, example = "我只是描述")
    private String description;
    @ApiModelProperty(value = "优惠劵类型", required = true, example = "参见 CouponTemplateTypeEnum 枚举")
    private Integer type;
    /**
     * 码类型
     *
     * 1-一卡一码（UNIQUE）
     * 2-通用码（GENERAL）
     *
     * 【优惠码独有】 @see CouponCodeDO
     */
    // TODO
    private Integer codeType;
    @ApiModelProperty(value = "优惠码状态", required = true, example = "参见 CouponTemplateStatusEnum 枚举")
    private Integer status;
    @ApiModelProperty(value = "每人限领个数", example = "null - 则表示不限制")
    private Integer quota;
    @ApiModelProperty(value = "发放总量")
    private Integer total;
    // ========== 领取规则 END ==========

    // ========== 使用规则 BEGIN ==========
    @ApiModelProperty(value = "是否设置满多少金额可用，单位：分", required = true, example = "0-不限制；大于0-多少金额可用")
    private Integer priceAvailable;
    @ApiModelProperty(value = "可用范围的类型", required = true, example = "参见 CouponTemplateRangeTypeEnum 枚举")
    private Integer rangeType;
    @ApiModelProperty(value = "指定商品 / 分类列表，使用逗号分隔商品编号", example = "参见 CouponTemplateRangeTypeEnum 枚举")
    private String rangeValues;
    @ApiModelProperty(value = "生效日期类型", example = "参见 CouponTemplateDateTypeEnum 枚举")
    private Integer dateType;
    @ApiModelProperty(value = "固定日期-生效开始时间")
    private Date validStartTime;
    @ApiModelProperty(value = "固定日期-生效结束时间")
    private Date validEndTime;
    @ApiModelProperty(value = "领取日期-开始天数", example = "例如，0-当天；1-次天")
    private Integer fixedStartTerm;
    @ApiModelProperty(value = "领取日期-结束天数")
    private Integer fixedEndTerm;
    // ========== 使用规则 END ==========

    // ========== 使用效果 BEGIN ==========
    @ApiModelProperty(value = "优惠类型", example = "参见 CouponTemplatePreferentialTypeEnum 枚举")
    private Integer preferentialType;
    @ApiModelProperty(value = "折扣百分比")
    private Integer percentOff;
    @ApiModelProperty(value = "优惠金额，单位：分")
    private Integer priceOff;
    @ApiModelProperty(value = "折扣上限")
    private Integer discountPriceLimit;
    // ========== 使用效果 END ==========

    // ========== 统计信息 BEGIN ==========
    @ApiModelProperty(value = "折扣上限", required = true)
    private Integer statFetchNum;
    // ========== 统计信息 END ==========

    @ApiModelProperty(value = "折扣上限", required = true)
    private Date createTime;

}
