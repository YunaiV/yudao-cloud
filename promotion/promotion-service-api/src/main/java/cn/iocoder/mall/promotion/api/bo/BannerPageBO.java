package cn.iocoder.mall.promotion.api.bo;

import java.util.List;

public class BannerPageBO {

    /**
     * Banner 数组
     */
    private List<BannerBO> list;
    /**
     * 总量
     */
    private Integer total;

    public List<BannerBO> getList() {
        return list;
    }

    public BannerPageBO setList(List<BannerBO> list) {
        this.list = list;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public BannerPageBO setTotal(Integer total) {
        this.total = total;
        return this;
    }

}