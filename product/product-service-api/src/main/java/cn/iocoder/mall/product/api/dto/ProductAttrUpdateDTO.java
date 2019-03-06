package cn.iocoder.mall.product.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Product 规格修改 DTO
 */
public class ProductAttrUpdateDTO {

    /**
     * 规格编号
     */
    @NotNull(message = "规格编号不能为空")
    private Integer id;
    /**
     * 名称
     */
    @NotEmpty(message = "规格名不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public ProductAttrUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ProductAttrUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }

}