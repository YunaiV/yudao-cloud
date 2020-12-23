package cn.iocoder.mall.systemservice.rpc.permission.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * 赋予管理员角色 DTO
 */
@Data
@Accessors(chain = true)
public class PermissionAssignAdminRoleDTO implements Serializable {

    /**
     * 管理员编号
     */
    @NotNull(message = "管理员编号不能为空")
    private Integer adminId;
    /**
     * 角色编号列表
     */
    private Set<Integer> roleIds;

}
