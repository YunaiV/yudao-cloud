package cn.iocoder.mall.product.api.bo;

/**
 * 商品规格值 VO
 */
public class ProductAttrValueSimpleBO {

    /**
     * 规格值编号
     */
    private Integer id;
    /**
     * 规格值名
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public ProductAttrValueSimpleBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductAttrValueSimpleBO setName(String name) {
        this.name = name;
        return this;
    }

}