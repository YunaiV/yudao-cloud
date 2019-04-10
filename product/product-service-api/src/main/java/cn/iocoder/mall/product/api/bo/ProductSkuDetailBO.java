package cn.iocoder.mall.product.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品 Sku 明细 BO（包括 Spu 明细）
 */
@Data
@Accessors(chain = true)
public class ProductSkuDetailBO implements Serializable {

    /**
     * sku 编号
     */
    private Integer id;
    /**
     * SPU 信息
     */
    private Spu spu;
    /**
     * 图片地址
     */
    private String picURL;
    /**
     * 规格值数组
     */
    private List<ProductAttrAndValuePairBO> attrs;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;

    @Data
    @Accessors(chain = true)
    public static class Spu implements Serializable {

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

    }

}
