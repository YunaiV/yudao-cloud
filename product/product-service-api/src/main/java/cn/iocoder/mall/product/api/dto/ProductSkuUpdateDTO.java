package cn.iocoder.mall.product.api.dto;

import java.util.List;

/**
 * 商品 Sku 更新 DTO
 */
public class ProductSkuUpdateDTO {

    /**
     * 规格值数组
     */
    private List<Integer> attrs;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;

    public List<Integer> getAttrs() {
        return attrs;
    }

    public ProductSkuUpdateDTO setAttrs(List<Integer> attrs) {
        this.attrs = attrs;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ProductSkuUpdateDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductSkuUpdateDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
    
}