package cn.iocoder.mall.admin.api.bo;

import java.io.Serializable;
import java.util.Set;

public class OAuth2AuthenticationBO implements Serializable {

    /**
     * 管理员编号
     */
    private Integer adminId;
    /**
     * 角色编号数组
     */
    private Set<Integer> roleIds;

    public Integer getAdminId() {
        return adminId;
    }

    public OAuth2AuthenticationBO setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }

    public Set<Integer> getRoleIds() {
        return roleIds;
    }

    public OAuth2AuthenticationBO setRoleIds(Set<Integer> roleIds) {
        this.roleIds = roleIds;
        return this;
    }

}