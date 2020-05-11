package cn.iocoder.mall.product.rest.request.attr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@ApiModel("商品 - 规格模块 - 商品规格添加 Request")
@Data
@Accessors(chain = true)
public class ProductAttrAddRequest {

    @ApiModelProperty(name = "name", value = "规格名", required = true, example = "颜色")
    @NotEmpty(message = "规格名不能为空")
    private String name;

}
