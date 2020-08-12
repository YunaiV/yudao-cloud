package cn.iocoder.mall.productservice.service.sku.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * 商品 SKU 列表查询 BO
 */
@Data
@Accessors(chain = true)
public class ProductSkuListQueryBO {

    /**
     * 商品 SKU 编号
     */
    private Integer productSkuId;
    /**
     * 商品 SKU 编号数组
     */
    private Collection<Integer> productSkuIds;
    /**
     * 商品 SPU 编号
     */
    private Integer productSpuId;
    /**
     * 商品 SKU 状态
     */
    private Integer productSkuStatus;

}
