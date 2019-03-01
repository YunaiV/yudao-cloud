package cn.iocoder.mall.admin.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 角色添加 DTO
 */
public class RoleUpdateDTO {

    /**
     * 角色编号
     */
    @NotNull(message = "角色编号不能为空")
    private Integer id;
    /**
     * 角色名字（标识）
     */
    @NotEmpty(message = "角色名字不能为空")
    private String name;

    public Integer getId() {
        return id;
    }

    public RoleUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

}