package cn.iocoder.mall.product.biz.dto.category;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - 更新商品分类状态DTO
 */
@Data
@Accessors(chain = true)
public class ProductCategoryUpdateStatusDTO {

    /**
     * 管理员id
     */
    private Integer adminId;
    /**
     * 商品分类编号
     */
    private Integer id;
    /**
     * 状态
     */
    private Integer status;

}
