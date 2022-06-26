package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "商品规格属性和值对 VO")
@Data
@Accessors(chain = true)
public class AdminsProductAttrAndValuePairVO {

    @ApiModelProperty(value = "规格编号", required = true, example = "1")
    private Integer attrId;
    @ApiModelProperty(value = "规格名", required = true, example = "颜色")
    private String attrName;
    @ApiModelProperty(value = "规格值", required = true, example = "10")
    private Integer attrValueId;
    @ApiModelProperty(value = "规格值名", required = true, example = "红色")
    private String attrValueName;

}
