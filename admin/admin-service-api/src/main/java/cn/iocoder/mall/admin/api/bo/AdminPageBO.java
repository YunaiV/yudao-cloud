package cn.iocoder.mall.admin.api.bo;

import java.util.List;

public class AdminPageBO {

    /**
     * 管理员数组
     */
    private List<AdminBO> admins;
    /**
     * 总量
     */
    private Integer count;

    public List<AdminBO> getAdmins() {
        return admins;
    }

    public AdminPageBO setAdmins(List<AdminBO> admins) {
        this.admins = admins;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public AdminPageBO setCount(Integer count) {
        this.count = count;
        return this;
    }

}