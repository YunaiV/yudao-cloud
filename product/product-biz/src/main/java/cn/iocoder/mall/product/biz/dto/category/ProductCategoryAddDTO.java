package cn.iocoder.mall.product.biz.dto.category;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - 创建商品分类DTO
 */
@Data
@Accessors(chain = true)
public class ProductCategoryAddDTO {

    /**
     * 管理员id
     */
    @NotNull(message = "管理员id不能为空")
    private Integer adminId;

    /**
     * 父分类编号
     */
    @NotNull(message = "父分类编号不能为空")
    private Integer pid;
    /**
     * 名称
     */
    @NotNull(message = "名称不能为空")
    private String name;
    /**
     * 描述
     */
    @NotNull(message = "描述不能为空")
    private String description;
    /**
     * 分类图片
     */
    private String picUrl;
    /**
     * 排序值
     */
    @NotNull(message = "排序值不能为空")
    private Integer sort;

}
