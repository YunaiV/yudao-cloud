package cn.iocoder.mall.managementweb.controller.promotion.recommend.vo;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.common.framework.vo.PageParam;
import cn.iocoder.mall.promotion.api.enums.recommend.ProductRecommendTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("商品推荐分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductRecommendPageReqVO extends PageParam {

    @ApiModelProperty(value = "类型", example = "1", notes = "参见 ProductRecommendTypeEnum 枚举")
    @InEnum(value = ProductRecommendTypeEnum.class, message = "推荐类型必须是 {value}")
    private Integer type;

}
