package cn.iocoder.mall.systemservice.rpc.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignAdminRoleDTO;
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
    CommonResult<Set<Integer>> listRoleResourceIds(Integer roleId);

    /**
     * 赋予角色资源
     *
     * @param assignRoleResourceDTO 赋予角色资源 DTO
     * @return 成功
     */
    CommonResult<Boolean> assignRoleResource(PermissionAssignRoleResourceDTO assignRoleResourceDTO);

    /**
     * 获得管理员拥有的角色编号列表
     *
     * @param adminId 管理员编号
     * @return 资源编号列表
     */
    CommonResult<Set<Integer>> listAdminRoleIds(Integer adminId);

    /**
     * 赋予管理员角色
     *
     * @param assignAdminRoleDTO 赋予管理员角色 DTO
     * @return 成功
     */
    CommonResult<Boolean> assignAdminRole(PermissionAssignAdminRoleDTO assignAdminRoleDTO);

}
