package cn.iocoder.mall.admin.api.bo;

import java.util.Date;

/**
 * 资源 BO
 */
public class ResourceBO {

    /**
     * 资源编号
     */
    private Integer id;
    /**
     * 资源名字（标识）
     */
    private String name;
    /**
     * 资源类型
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 展示名
     */
    private String displayName;
    /**
     * 添加时间
     */
    private Date createTime;
    /**
     * 父级资源编号
     */
    private Integer pid;
    /**
     * 操作
     */
    private String handler;

    public Integer getId() {
        return id;
    }

    public ResourceBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResourceBO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ResourceBO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ResourceBO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ResourceBO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ResourceBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public ResourceBO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getHandler() {
        return handler;
    }

    public ResourceBO setHandler(String handler) {
        this.handler = handler;
        return this;
    }

}