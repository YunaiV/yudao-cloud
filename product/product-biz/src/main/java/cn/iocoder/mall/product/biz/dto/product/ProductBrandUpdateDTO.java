package cn.iocoder.mall.product.biz.dto.product;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Product 品牌添加 DTO
 */
@Data
@Accessors(chain = true)
public class ProductBrandUpdateDTO {

    /**
     * 主键
     */
    @NotNull(message = "品牌主键不能为空")
    private Integer id;

    /**
     * 名称
     */
    @NotEmpty(message = "品牌名不能为空")
    private String name;

    /**
     * 描述
     */
    @NotEmpty(message = "品牌描述不能为空")
    private String description;

    /**
     * 图片地址
     */
    @NotEmpty(message = "品牌图片地址不能为空")
    private String picUrl;

    /**
     * 状态
     *
     * 1-开启
     * 2-禁用
     */
    @NotNull(message = "品牌状态不能为空")
    private Integer status;

}
