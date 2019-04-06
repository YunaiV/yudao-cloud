package cn.iocoder.mall.product.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Product 规格值添加 DTO
 */
@Data
@Accessors(chain = true)
public class ProductAttrValueAddDTO {

    /**
     * 规格编号
     */
    @NotNull(message = "规格编号不能为空")
    private Integer attrId;
    /**
     * 名称
     */
    @NotEmpty(message = "规格值名不能为空")
    private String name;

}
