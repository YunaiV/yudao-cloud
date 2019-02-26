package cn.iocoder.mall.admin.dataobject;

import java.util.Date;

/**
 * 角色实体
 */
public class RoleDO {

    /**
     * 账号状态 - 开启
     */
    public static final Integer STATUS_ENABLE = 1;
    /**
     * 账号状态 - 禁用
     */
    public static final Integer STATUS_DISABLE = 2;

    /**
     * 角色编号
     */
    private Integer id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态
     */
    private Integer status;

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

    public Date getCreateTime() {
        return createTime;
    }

    public RoleDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public RoleDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

}
