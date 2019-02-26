package cn.iocoder.mall.admin.sdk.context;

import java.util.Set;

/**
 * Security 上下文
 */
public class AdminSecurityContext {

    private final Integer adminId;
    private final Set<Integer> roleIds;

    public AdminSecurityContext(Integer adminId, Set<Integer> roleIds) {
        this.adminId = adminId;
        this.roleIds = roleIds;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public Set<Integer> getRoleIds() {
        return roleIds;
    }

}