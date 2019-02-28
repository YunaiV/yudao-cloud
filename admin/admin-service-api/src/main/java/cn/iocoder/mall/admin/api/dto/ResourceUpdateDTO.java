package cn.iocoder.mall.admin.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 资源更新 DTO
 */
public class ResourceUpdateDTO {

    /**
     * 资源编号
     */
    @NotNull(message = "资源编号不能为空")
    private Integer id;
    /**
     * 资源名字（标识）
     */
    @NotEmpty(message = "资源名字不能为空")
    private String name;
    /**
     * 排序值
     */
    @NotNull(message = "类型不能为空")
    private Integer sort;
    /**
     * 展示名
     */
    @NotEmpty(message = "资源名字不能为空")
    private String displayName;
    /**
     * 父资源编号
     */
    private Integer pid;
    /**
     * 操作
     */
    private String handler;

    public Integer getId() {
        return id;
    }

    public ResourceUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResourceUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ResourceUpdateDTO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ResourceUpdateDTO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public ResourceUpdateDTO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getHandler() {
        return handler;
    }

    public ResourceUpdateDTO setHandler(String handler) {
        this.handler = handler;
        return this;
    }

}