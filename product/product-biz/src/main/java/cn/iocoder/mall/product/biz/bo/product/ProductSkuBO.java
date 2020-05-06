package cn.iocoder.mall.product.biz.bo.product;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品 SKU BO
 */
@Data
@Accessors(chain = true)
public class ProductSkuBO implements Serializable {

    /**
     * sku 编号
     */
    private Integer id;
    /**
     * 商品编号
     */
    private Integer spuId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 图片地址
     */
    private String picURL;
//    /**
//     * 规格值数组
//     */
//    // TODO 芋艿，这个属性目前未进行设置
//    private List<ProductAttrAndValuePairBO> attrs;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;

}
