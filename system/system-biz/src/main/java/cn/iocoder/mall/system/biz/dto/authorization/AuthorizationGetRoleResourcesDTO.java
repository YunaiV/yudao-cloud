package cn.iocoder.mall.system.biz.dto.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 授权模块 - 获得角色拥有资源集合 DTO
 */
@Data
@Accessors(chain = true)
public class AuthorizationGetRoleResourcesDTO {

    @NotNull(message = "角色编号不能为空")
    private Integer roleId;

}
