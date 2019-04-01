package cn.iocoder.mall.product.api.bo;

import java.io.Serializable;
import java.util.List;

public class ProductSpuPageBO implements Serializable {

    /**
     * Spu 数组
     */
    private List<ProductSpuBO> spus;
    /**
     * 总量
     */
    private Integer count;

    public List<ProductSpuBO> getSpus() {
        return spus;
    }

    public ProductSpuPageBO setSpus(List<ProductSpuBO> spus) {
        this.spus = spus;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public ProductSpuPageBO setCount(Integer count) {
        this.count = count;
        return this;
    }

}