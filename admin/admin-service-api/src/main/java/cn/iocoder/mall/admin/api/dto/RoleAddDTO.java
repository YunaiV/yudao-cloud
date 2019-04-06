package cn.iocoder.mall.admin.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 角色添加 DTO
 */
@Data
@Accessors(chain = true)
public class RoleAddDTO {

    /**
     * 角色名字（标识）
     */
    @NotEmpty(message = "角色名字不能为空")
    private String name;

}
