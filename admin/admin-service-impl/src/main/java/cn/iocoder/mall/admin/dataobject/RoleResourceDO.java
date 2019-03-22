package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

/**
 * {@link RoleDO} 和 {@link ResourceDO} 的关联表
 */
public class RoleResourceDO extends DeletableDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 角色编号(外键：{@link RoleDO}
     */
    private Integer roleId;
    /**
     * 资源编号(外键：{@link ResourceDO}
     */
    private Integer resourceId;

    public Integer getId() {
        return id;
    }

    public RoleResourceDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public RoleResourceDO setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public RoleResourceDO setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
        return this;
    }

}