package cn.iocoder.mall.product.biz.dto.category;

import lombok.Data;
import lombok.experimental.Accessors;

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
    // TODO FROM 芋艿 to 伟帆：传入 Service 的，要加下 Validation 的注解，虽然 Controller 那也添加了 Validation，但是相比来说，Service 更应该被保护，嘿嘿。因为一些时候，Service 也会被别人所调用，所以要保护好自己。
    private Integer adminId;

    /**
     * 父分类编号
     */
    private Integer pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 分类图片
     */
    private String picUrl;
    /**
     * 排序值
     */
    private Integer sort;

}
