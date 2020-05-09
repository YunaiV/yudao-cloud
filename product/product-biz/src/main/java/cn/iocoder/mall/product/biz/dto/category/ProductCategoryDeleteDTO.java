package cn.iocoder.mall.product.biz.dto.category;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "管理员id不能为空")
    private Integer adminId;
    /**
     * 商品分类编号
     */
    @NotNull(message = "编号不能为空")
    private Integer id;

}
