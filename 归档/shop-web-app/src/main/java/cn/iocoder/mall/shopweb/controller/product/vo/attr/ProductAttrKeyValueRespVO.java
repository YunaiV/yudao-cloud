package cn.iocoder.mall.shopweb.controller.product.vo.attr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "商品规格 KEY + VALUE 对的 Response VO")
@Data
@Accessors(chain = true)
public class ProductAttrKeyValueRespVO {

    @ApiModelProperty(value = "规格 KEY 编号", required = true, example = "1")
    private Integer attrKeyId;
    @ApiModelProperty(value = "规格 KEY 名字", required = true, example = "颜色")
    private String attrKeyName;
    @ApiModelProperty(value = "规格 VALUE 值", required = true, example = "10")
    private Integer attrValueId;
    @ApiModelProperty(value = "规格 VALUE 名字", required = true, example = "红色")
    private String attrValueName;

}
