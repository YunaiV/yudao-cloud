package cn.iocoder.mall.system.biz.dto.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 授权模块 - 分配角色资源 DTO
 */
@Data
@Accessors(chain = true)
public class AuthorizationAssignRoleResourceDTO {

    @NotNull(message = "管理员编号不能为空")
    private Integer adminId;

    @NotNull(message = "角色编号不能为空")
    private Integer roleId;

    /**
     * 资源编号数组
     */
    private Set<Integer> resourceIds;

}
