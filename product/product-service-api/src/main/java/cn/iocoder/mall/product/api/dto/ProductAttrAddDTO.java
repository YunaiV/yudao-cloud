package cn.iocoder.mall.product.api.dto;

import javax.validation.constraints.NotEmpty;

/**
 * Product 规格添加 DTO
 */
public class ProductAttrAddDTO {

    /**
     * 名称
     */
    @NotEmpty(message = "规格名不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public ProductAttrAddDTO setName(String name) {
        this.name = name;
        return this;
    }

}