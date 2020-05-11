package cn.iocoder.mall.product.rest.request.attr;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 规格添加
 */
@Data
@Accessors(chain = true)
public class ProductAttrAddRequest {

    @ApiModelProperty(name = "name", value = "规格名", required = true, example = "颜色")
    @NotEmpty(message = "规格名不能为空")
    private String name;

}
