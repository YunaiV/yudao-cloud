package cn.iocoder.mall.productservice.enums.sku;

import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;

/**
 * 商品 SKU 明细的字段枚举
 *
 * @see ProductSkuRespDTO
 */
public enum ProductSkuDetailFieldEnum {

    SPU("spu"),
    ATTR("attr");

    /**
     * 字段
     */
    private final String field;

    ProductSkuDetailFieldEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
