package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * 角色实体
 */
public class RoleDO extends BaseDO {

    /**
     * 角色编号
     */
    private Integer id;
    /**
     * 角色名
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public RoleDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleDO setName(String name) {
        this.name = name;
        return this;
    }

}
