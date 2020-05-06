package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "商品规格值精简 VO")
@Data
@Accessors(chain = true)
public class AdminsProductAttrValueSimpleVO {

    @ApiModelProperty(value = "规格值编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "规格值名", required = true, example = "颜色")
    private String name;

}
