package cn.iocoder.mall.product.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 商品 Spu 分页 DTO
 */
@Data
@Accessors(chain = true)
public class ProductSpuPageDTO {

    /**
     * 商品名
     *
     * 模糊匹配
     */
    private String name;
    /**
     * 分类编号
     */
    private Integer cid;
    /**
     * 是否可见
     */
    private Boolean visible;
    /**
     * 是否有库存
     *
     * 允许为空。空时，不进行筛选
     */
    private Boolean hasQuantity;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
