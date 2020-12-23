package cn.iocoder.mall.productservice.rpc.spu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
* 商品 SPU 和 SKU 更新 Request DTO
*/
@Data
@Accessors(chain = true)
public class ProductSpuAndSkuUpdateReqDTO implements Serializable {

    /**
     * SKU 信息
     */
    @Data
    @Accessors(chain = true)
    public static class Sku implements Serializable {

        /**
         * 规格值数组
         */
        @NotNull(message = "规格值数组不能为空")
        private List<Integer> attrValueIds;
        /**
         * 价格，单位：分
         */
        @NotNull(message = "价格不能为空")
        @Min(value = 1L, message = "最小价格为 1")
        private Integer price;
        /**
         * 库存数量
         */
        @NotNull(message = "库存数量不能为空")
        @Min(value = 1L, message = "最小库存为 1")
        private Integer quantity;

    }

    /**
     * Spu 编号
     */
    @NotNull(message = "SPU 编号不能为空")
    private Integer id;

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
    @NotNull(message = "分类编号不能为空")
    private Integer cid;
    /**
     * 商品主图地址
     */
    @NotEmpty(message = "商品主图地址不能为空")
    private List<String> picUrls;

    // ========== 其他信息 =========
    /**
     * 是否上架商品
     */
    @NotNull(message = "是否上架商品不能为空")
    private Boolean visible;

    // ========== SKU =========

    /**
     * SKU 数组
     */
    @NotNull(message = "SKU 不能为空")
    @Valid
    private List<Sku> skus;

}
