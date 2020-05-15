package cn.iocoder.mall.product.biz.dto.sku;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 商品 SPU + SKU 添加 DTO
 */
@Data
@Accessors(chain = true)
// TODO FROM 芋艿 to sunderui && q2118cs：貌似重复了，只要保留一个哈
public class ProductSpuAddDTO {

    // ========== 基本信息 =========
    /**
     * SPU 名字
     */
    @NotEmpty(message = "SPU 名字不能为空")
    private String name;
    /**
     * 卖点
     */
    @NotEmpty(message = "卖点不能为空")
    private String sellPoint;
    /**
     * 描述
     */
    @NotEmpty(message = "描述不能为空")
    private String description;
    /**
     * 分类编号
     */
    @NotNull(message = "分类不能为空")
    private Integer cid;
    /**
     * 商品主图地址
     */
    @NotNull(message = "商品主图不能为空")
    private List<String> picUrls;

    // ========== 其他信息 =========
    /**
     * 是否上架商品（是否可见）。
     *
     * true 为已上架
     * false 为已下架
     */
    @NotNull(message = "是否上架不能为空")
    private Boolean visible;

    // ========== SKU =========

    /**
     * SKU 数组
     */
    @NotNull(message = "SKU 不能为空")
    private List<ProductSkuAddOrUpdateDTO> skus;

}
