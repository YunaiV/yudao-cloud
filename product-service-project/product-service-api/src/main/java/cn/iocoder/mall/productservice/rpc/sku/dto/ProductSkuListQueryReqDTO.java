package cn.iocoder.mall.productservice.rpc.sku.dto;

import cn.iocoder.mall.productservice.enums.sku.ProductSkuDetailFieldEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 商品 SKU 列表查询 DTO
 */
@Data
@Accessors(chain = true)
public class ProductSkuListQueryReqDTO implements Serializable {

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
     * 额外返回字段
     *
     * @see ProductSkuDetailFieldEnum
     */
    private Collection<String> fields = Collections.emptySet();

}
