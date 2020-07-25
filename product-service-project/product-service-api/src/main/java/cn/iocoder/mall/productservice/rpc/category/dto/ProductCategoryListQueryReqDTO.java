package cn.iocoder.mall.productservice.rpc.category.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品分类列表查询 DTO
 */
@Data
@Accessors(chain = true)
public class ProductCategoryListQueryReqDTO implements Serializable {

    /**
     * 父编号
     */
    private Integer pid;

}
