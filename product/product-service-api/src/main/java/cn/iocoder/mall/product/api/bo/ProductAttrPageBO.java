package cn.iocoder.mall.product.api.bo;

import java.util.List;

/**
 * 商品规格明细分页 BO
 */
public class ProductAttrPageBO {

    /**
     * 规格数组
     */
    private List<ProductAttrDetailBO> attrs;
    /**
     * 总数
     */
    private Integer count;

    public List<ProductAttrDetailBO> getAttrs() {
        return attrs;
    }

    public ProductAttrPageBO setAttrs(List<ProductAttrDetailBO> attrs) {
        this.attrs = attrs;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public ProductAttrPageBO setCount(Integer count) {
        this.count = count;
        return this;
    }

}