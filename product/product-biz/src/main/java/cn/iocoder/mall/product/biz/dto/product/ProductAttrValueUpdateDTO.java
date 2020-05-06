package cn.iocoder.mall.product.biz.dto.product;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Product 规格值修改 DTO
 *
 * 注意，不允许修改所属规格
 */
@Data
@Accessors(chain = true)
public class ProductAttrValueUpdateDTO {

    /**
     * 规格值编号
     */
    @NotNull(message = "规格编号不能为空")
    private Integer id;
    /**
     * 名称
     */
    @NotEmpty(message = "规格名不能为空")
    private String name;

}
