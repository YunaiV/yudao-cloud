package cn.iocoder.mall.product.biz.dto.category;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - 删除商品分类DTO
 */
@Data
@Accessors(chain = true)
public class ProductCategoryDeleteDTO {

    /**
     * 管理员id
     */
    private Integer adminId;
    /**
     * 商品分类编号
     */
    private Integer id;

}
