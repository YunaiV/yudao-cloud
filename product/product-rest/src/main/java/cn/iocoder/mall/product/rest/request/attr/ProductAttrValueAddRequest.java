package cn.iocoder.mall.product.rest.request.attr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("商品 - 规格模块 - 商品规格值添加 Request")
@Data
@Accessors(chain = true)
public class ProductAttrValueAddRequest {

    @ApiModelProperty(name = "attrId", value = "规格编号", required = true, example = "1")
    @NotNull(message = "规格编号不能为空")
    private Integer attrId;
    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "规格值名", required = true, example = "红色")
    @NotEmpty(message = "规格值名不能为空")
    private String name;

}
