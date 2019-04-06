package cn.iocoder.mall.promotion.api.bo;

import java.util.List;

public class CouponTemplatePageBO {

    /**
     * 优惠劵（码）数组
     */
    private List<CouponTemplateBO> list;
    /**
     * 总量
     */
    private Integer total;

    public CouponTemplatePageBO setList(List<CouponTemplateBO> list) {
        this.list = list;
        return this;
    }

    public CouponTemplatePageBO setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public List<CouponTemplateBO> getList() {
        return list;
    }

    public Integer getTotal() {
        return total;
    }
}
