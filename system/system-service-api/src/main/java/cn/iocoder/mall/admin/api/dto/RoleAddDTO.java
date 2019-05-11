package cn.iocoder.mall.admin.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 角色添加 DTO
 */
@Data
@Accessors(chain = true)
public class RoleAddDTO implements Serializable {

    /**
     * 角色名字（标识）
     */
    @NotEmpty(message = "角色名字不能为空")
    private String name;

}
