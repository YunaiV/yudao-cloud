package cn.iocoder.mall.admin.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 资源添加 DTO
 */
public class ResourceAddDTO {

    /**
     * 资源名字（标识）
     */
    @NotEmpty(message = "资源名字不能为空")
    private String name;
    /**
     * 类型
     */
    @NotNull(message = "类型不能为空")
    private Integer type;
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
     * 父资源比那好
     */
    private Integer pid;
    /**
     * 操作
     */
    private String handler;

    public String getName() {
        return name;
    }

    public ResourceAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ResourceAddDTO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ResourceAddDTO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ResourceAddDTO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public ResourceAddDTO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getHandler() {
        return handler;
    }

    public ResourceAddDTO setHandler(String handler) {
        this.handler = handler;
        return this;
    }

}