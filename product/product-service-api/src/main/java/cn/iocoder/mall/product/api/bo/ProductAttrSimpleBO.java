package cn.iocoder.mall.product.api.bo;

import java.util.List;

/**
 * 商品规格精简 VO
 */
public class ProductAttrSimpleBO {

    /**
     * 规格编号
     */
    private Integer id;
    /**
     * 规格名
     */
    private String name;
    /**
     * 规格值数组
     */
    private List<ProductAttrValueSimpleBO> values;

    public Integer getId() {
        return id;
    }

    public ProductAttrSimpleBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductAttrSimpleBO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ProductAttrValueSimpleBO> getValues() {
        return values;
    }

    public ProductAttrSimpleBO setValues(List<ProductAttrValueSimpleBO> values) {
        this.values = values;
        return this;
    }

}