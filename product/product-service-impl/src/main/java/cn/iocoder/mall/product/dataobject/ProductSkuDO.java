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
    private Integer spuId;

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
    private String picUrl;
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


    public Integer getId() {
        return id;
    }

    public ProductSkuDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public ProductSkuDO setSpuId(Integer spuId) {
        this.spuId = spuId;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductSkuDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public ProductSkuDO setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    public String getAttrs() {
        return attrs;
    }

    public ProductSkuDO setAttrs(String attrs) {
        this.attrs = attrs;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ProductSkuDO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductSkuDO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

}