package cn.iocoder.mall.managementweb.controller.promotion.recommend.vo;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.enums.recommend.ProductRecommendTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("商品推荐更新 Request VO")
@Data
public class ProductRecommendUpdateReqVO {

    @ApiModelProperty(value = "编号", required = true, example = "1")
    @NotNull(message = "编号不能为空")
    private Integer id;
    @ApiModelProperty(value = "类型", example = "1", required = true, notes = "参见 ProductRecommendTypeEnum 枚举")
    @NotNull(message = "类型不能为空")
    @InEnum(value = ProductRecommendTypeEnum.class, message = "推荐类型必须是 {value}")
    private Integer type;
    @ApiModelProperty(value = "商品 Spu 编号", required = true, example = "1")
    @NotNull(message = "商品 Spu 编号不能为空")
    private Integer productSpuId;
    @ApiModelProperty(value = "排序", required = true, example = "1")
    @NotNull(message = "排序不能为空")
    private Integer sort;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "参见 CommonStatusEnum 枚举")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;
    @ApiModelProperty(value = "备注", example = "我是备注")
    private String memo;

}
