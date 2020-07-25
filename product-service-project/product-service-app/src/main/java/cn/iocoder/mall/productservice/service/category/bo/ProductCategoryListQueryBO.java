package cn.iocoder.mall.productservice.service.category.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品分类列表查询 BO
 */
@Data
@Accessors(chain = true)
public class ProductCategoryListQueryBO {

    /**
     * 父编号
     */
    private Integer pid;

}
