package cn.iocoder.mall.admin.dataobject;

import java.util.Date;

/**
 * {@link RoleDO} 和 {@link ResourceDO} 的关联表
 */
public class RoleResourceDO {

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
    /**
     * 创建时间
     */
    private Date createTime;

    // TODO 芋艿 删除状态

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

    public Date getCreateTime() {
        return createTime;
    }

    public RoleResourceDO setCreateTime(Date createTime) {
        this.createTime = createTime;
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