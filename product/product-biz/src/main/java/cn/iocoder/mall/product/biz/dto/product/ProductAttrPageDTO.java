package cn.iocoder.mall.product.biz.dto.product;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 商品规格分页 DTO
 */
@Data
@Accessors(chain = true)
public class ProductAttrPageDTO {

    private String name;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
