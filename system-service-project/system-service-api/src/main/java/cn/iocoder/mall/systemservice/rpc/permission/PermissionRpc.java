package cn.iocoder.mall.systemservice.rpc.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignAdminRoleDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignRoleResourceDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionCheckDTO;

import java.util.Collection;
import java.util.Map;
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
     * 获得每个管理员拥有的角色编号
     * 返回的结果，key 为管理员编号
     *
     * @param adminIds 管理员编号列表
     * @return 每个管理员拥有的角色编号
     */
    CommonResult<Map<Integer, Set<Integer>>> mapAdminRoleIds(Collection<Integer> adminIds);

    /**
     * 赋予管理员角色
     *
     * @param assignAdminRoleDTO 赋予管理员角色 DTO
     * @return 成功
     */
    CommonResult<Boolean> assignAdminRole(PermissionAssignAdminRoleDTO assignAdminRoleDTO);

    /**
     * 校验管理员是否拥有指定权限。
     *
     * 如果没有，则抛出 {@link cn.iocoder.common.framework.exception.ServiceException} 异常
     *
     * @param checkDTO 校验权限 DTO
     * @return 成功
     */
    CommonResult<Boolean> checkPermission(PermissionCheckDTO checkDTO);

}
