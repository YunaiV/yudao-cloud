package cn.iocoder.mall.admin.api.bo;

import java.util.Date;

/**
 * 角色 BO
 */
public class RoleBO {

    /**
     * 角色编号
     */
    private Integer id;
    /**
     * 角色名字
     */
    private String name;
    /**
     * 添加时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public RoleBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleBO setName(String name) {
        this.name = name;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public RoleBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

}