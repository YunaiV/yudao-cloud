package cn.iocoder.mall.productservice.rpc.spu.dto;

import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyValueRespDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品 SPU 明细 Response DTO
 */
@Data
@Accessors(chain = true)
public class ProductSpuDetailRespDTO implements Serializable {

    /**
     * SPU 编号
     */
    private Integer id;

    // ========== 基本信息 =========
    /**
     * SPU 名字
     */
    private String name;
    /**
     * 卖点
     */
    private String sellPoint;
    /**
     * 描述
     */
    private String description;
    /**
     * 分类编号
     */
    private Integer cid;
    /**
     * 分类名
     */
    private String categoryName;
    /**
     * 商品主图地址
     *
     * 数组，以逗号分隔
     *
     * 建议尺寸：800*800像素，你可以拖拽图片调整顺序，最多上传15张
     */
    private List<String> picUrls;

    // ========== 其他信息 =========
    /**
     * 是否上架商品（是否可见）。
     *
     * true 为已上架
     * false 为已下架
     */
    private Boolean visible;
    /**
     * 排序字段
     */
    private Integer sort;

    // ========== SKU =========

    /**
     * SKU 数组
     */
    private List<Sku> skus;

    /**
     * 商品 Sku 明细
     */
    @Data
    @Accessors(chain = true)
    public static class Sku implements Serializable {

        private Integer id;
        /**
         * 商品编号
         */
        private Integer spuId;
        /**
         * 图片地址
         */
        private String picURL;
        /**
         * 规格值数组
         */
        private List<ProductAttrKeyValueRespDTO> attrs;
        /**
         * 价格，单位：分
         */
        private Integer price;
        /**
         * 库存数量
         */
        private Integer quantity;

    }

}
