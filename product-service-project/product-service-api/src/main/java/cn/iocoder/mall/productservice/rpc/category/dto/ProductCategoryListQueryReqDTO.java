package cn.iocoder.mall.productservice.rpc.category.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品分类列表查询 DTO
 */
@Data
@Accessors(chain = true)
public class ProductCategoryListQueryReqDTO {

    /**
     * 父编号
     */
    private Integer pid;

}
