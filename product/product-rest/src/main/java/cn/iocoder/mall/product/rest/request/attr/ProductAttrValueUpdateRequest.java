package cn.iocoder.mall.product.rest.request.attr;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Product 规格值修改 DTO
 * <p>
 * 注意，不允许修改所属规格
 */
@Data
@Accessors(chain = true)
public class ProductAttrValueUpdateRequest {

    @ApiModelProperty(name = "id", value = "规格值编号", required = true, example = "1")
    @NotNull(message = "规格值编号不能为空")
    private Integer id;

    @ApiModelProperty(name = "id", value = "规格值编号", required = true, example = "1")
    @NotEmpty(message = "规格名不能为空")
    private String name;

}
