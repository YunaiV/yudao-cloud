package cn.iocoder.mall.product.rest.request.attr;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 规格修改
 */
@Data
@Accessors(chain = true)
public class ProductAttrUpdateRequest {

    @ApiModelProperty(name = "id", value = "规格编号", required = true, example = "1")
    @NotNull(message = "规格编号不能为空")
    private Integer id;

    @ApiModelProperty(name = "name", value = "规格名", required = true, example = "颜色")
    @NotEmpty(message = "规格名不能为空")
    private String name;


}
