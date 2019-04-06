package cn.iocoder.mall.admin.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 角色添加 DTO
 */
@Data
@Accessors(chain = true)
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

}
