package cn.iocoder.mall.systemservice.rpc.permission.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * 赋予角色资源 DTO
 */
@Data
@Accessors(chain = true)
public class PermissionAssignRoleResourceDTO implements Serializable {

    /**
     * 角色编号
     */
    @NotNull(message = "角色编号不能为空")
    private Integer roleId;
    /**
     * 资源编号列表
     */
    private Set<Integer> resourceIds;

}
