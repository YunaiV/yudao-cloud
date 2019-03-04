package cn.iocoder.mall.product.api.bo;

import java.util.List;

/**
 * 商品 Sku 明细 BO
 */
public class ProductSkuDetailBO {

    /**
     * sku 编号
     */
    private Integer id;
    /**
     * 商品编号
     */
    private Integer itemId;

    // TODO 店铺编号

    /**
     * 状态
     *
     * 1-正常
     * 2-禁用
     */
    private Integer status;
    /**
     * 图片地址
     */
    private String picURL;
    /**
     * 规格值数组
     */
    private List<ProductAttrBO> attrs;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;

}