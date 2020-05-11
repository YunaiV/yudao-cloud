package cn.iocoder.mall.product.biz.dto.attr;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Product 规格修改 DTO
 */
@Data
@Accessors(chain = true)
public class ProductAttrUpdateDTO {

    /**
     * 规格编号
     */
    @NotNull(message = "规格编号不能为空")
    private Integer id;
    /**
     * 名称
     */
    @NotEmpty(message = "规格名不能为空")
    private String name;


}
