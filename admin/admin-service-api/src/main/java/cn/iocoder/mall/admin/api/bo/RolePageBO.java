package cn.iocoder.mall.admin.api.bo;

import java.util.List;

public class RolePageBO {

    /**
     * 角色数组
     */
    private List<RoleBO> roles;
    /**
     * 总量
     */
    private Integer count;

    public List<RoleBO> getRoles() {
        return roles;
    }

    public RolePageBO setRoles(List<RoleBO> roles) {
        this.roles = roles;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public RolePageBO setCount(Integer count) {
        this.count = count;
        return this;
    }

}