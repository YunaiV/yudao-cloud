package cn.iocoder.mall.promotion.api.bo;

import java.util.List;

public class ProductRecommendPageBO {

    /**
     * ProductRecommend 数组
     */
    private List<ProductRecommendBO> list;
    /**
     * 总量
     */
    private Integer total;

    public List<ProductRecommendBO> getList() {
        return list;
    }

    public ProductRecommendPageBO setList(List<ProductRecommendBO> list) {
        this.list = list;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public ProductRecommendPageBO setTotal(Integer total) {
        this.total = total;
        return this;
    }

}