package cn.iocoder.mall.product.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 商品 Sku 添加 DTO
 */
public class ProductSkuAddOrUpdateDTO {

    /**
     * 规格值数组
     */
    @NotNull(message = "规格值数组不能为空")
    private List<Integer> attrs;
    /**
     * 价格，单位：分
     */
    @NotNull(message = "价格不能为空")
    @Min(value = 1L, message = "最小价格为 1")
    private Integer price;
    /**
     * 库存数量
     */
    @NotNull(message = "库存数量不能为空")
    @Min(value = 1L, message = "最小库存为 1")
    private Integer quantity;

    public List<Integer> getAttrs() {
        return attrs;
    }

    public ProductSkuAddOrUpdateDTO setAttrs(List<Integer> attrs) {
        this.attrs = attrs;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ProductSkuAddOrUpdateDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductSkuAddOrUpdateDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

}