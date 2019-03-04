package cn.iocoder.mall.product.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * 商品 SKU
 */
public class ProductSkuDO extends BaseDO {

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
     * 规格值({@link ProductAttrDO})数组
     *
     * 数组，以逗号分隔
     */
    private String attrs;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;
    //    /**
//     * 商品在付款减库存的状态下，该Sku上未付款的订单数量
//     */
//    private Integer withHoldQuantity;
//    /**
//     * 销量
//     */
//    private Integer soldNum;

}