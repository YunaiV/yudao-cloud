package cn.iocoder.mall.admin.api.dto;

import javax.validation.constraints.NotEmpty;

/**
 * 角色添加 DTO
 */
public class RoleAddDTO {

    /**
     * 角色名字（标识）
     */
    @NotEmpty(message = "角色名字不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public RoleAddDTO setName(String name) {
        this.name = name;
        return this;
    }

}