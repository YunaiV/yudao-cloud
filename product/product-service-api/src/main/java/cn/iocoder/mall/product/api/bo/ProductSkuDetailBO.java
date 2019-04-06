package cn.iocoder.mall.product.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品 Sku 明细 BO
 */
@Data
@Accessors(chain = true)
public class ProductSkuDetailBO implements Serializable {

    /**
     * sku 编号
     */
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
    private List<ProductAttrAndValuePairBO> attrs;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;

}
