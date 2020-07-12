package cn.iocoder.mall.systemservice.rpc.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignRoleResourceDTO;

import java.util.Set;

/**
 * 权限 Rpc 接口
 */
public interface PermissionRpc {

    /**
     * 获得角色拥有的资源编号
     *
     * @param roleId 角色编号
     * @return 资源编号列表
     */
    CommonResult<Set<Integer>> listRoleResourceId(Integer roleId);

    /**
     * 赋予角色资源
     *
     * @param assignRoleResourceDTO 赋予角色资源 DTO
     * @return 成功
     */
    CommonResult<Boolean> assignRoleResource(PermissionAssignRoleResourceDTO assignRoleResourceDTO);

}
