package cn.iocoder.mall.productservice.enums.spu;

import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuDetailRespDTO;

/**
 * 商品 SPU 明细的字段枚举
 *
 * @see ProductSpuDetailRespDTO
 */
public enum ProductSpuDetailFieldEnum {

    SKU("sku"),
    ATTR("attr");

    /**
     * 字段
     */
    private final String field;

    ProductSpuDetailFieldEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
