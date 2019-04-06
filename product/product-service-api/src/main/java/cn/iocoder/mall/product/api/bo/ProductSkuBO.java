package cn.iocoder.mall.product.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品 SKU BOA
 */
@Data
@Accessors(chain = true)
public class ProductSkuBO implements Serializable {

    /**
     * SKU 编号
     */
    private Integer id;

}
