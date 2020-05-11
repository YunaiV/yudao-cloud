package cn.iocoder.mall.product.biz.dto.attr;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * Product 规格添加 DTO
 */
@Data
@Accessors(chain = true)
public class ProductAttrAddDTO {

    /**
     * 名称
     */
    @NotEmpty(message = "规格名不能为空")
    private String name;

}
