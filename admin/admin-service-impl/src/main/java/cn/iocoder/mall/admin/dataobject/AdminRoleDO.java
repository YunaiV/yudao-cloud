package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * {@link AdminDO} 和 {@link RoleDO} 的关联表
 */
public class AdminRoleDO extends BaseDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 管理员编号(外键：{@link AdminDO}
     */
    private Integer adminId;
    /**
     * 角色编号(外键：{@link RoleDO}
     */
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public AdminRoleDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public AdminRoleDO setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public AdminRoleDO setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

}