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
    private Integer spuId;
    /**
     * 图片地址
     */
    private String picURL;
    /**
     * 规格值数组
     */
    private List<ProductAttrDetailBO> attrs;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;


    public Integer getId() {
        return id;
    }

    public ProductSkuDetailBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public ProductSkuDetailBO setSpuId(Integer spuId) {
        this.spuId = spuId;
        return this;
    }

    public String getPicURL() {
        return picURL;
    }

    public ProductSkuDetailBO setPicURL(String picURL) {
        this.picURL = picURL;
        return this;
    }

    public List<ProductAttrDetailBO> getAttrs() {
        return attrs;
    }

    public ProductSkuDetailBO setAttrs(List<ProductAttrDetailBO> attrs) {
        this.attrs = attrs;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ProductSkuDetailBO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductSkuDetailBO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

}