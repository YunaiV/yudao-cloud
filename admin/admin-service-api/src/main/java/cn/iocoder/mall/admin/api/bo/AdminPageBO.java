package cn.iocoder.mall.admin.api.bo;

import java.util.List;

public class AdminPageBO {

    /**
     * 管理员数组
     */
    private List<AdminBO> list;
    /**
     * 总量
     */
    private Integer total;

    public List<AdminBO> getList() {
        return list;
    }

    public AdminPageBO setList(List<AdminBO> list) {
        this.list = list;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public AdminPageBO setTotal(Integer total) {
        this.total = total;
        return this;
    }

}