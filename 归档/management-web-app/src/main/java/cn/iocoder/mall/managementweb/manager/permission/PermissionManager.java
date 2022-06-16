package cn.iocoder.mall.managementweb.manager.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.permission.dto.PermissionAssignAdminRoleDTO;
import cn.iocoder.mall.managementweb.controller.permission.dto.PermissionAssignRoleResourceDTO;
import cn.iocoder.mall.managementweb.convert.permission.PermissionConvert;
import cn.iocoder.mall.systemservice.rpc.permission.PermissionFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 权限 Manager
 */
@Service
public class PermissionManager {


    @Autowired
    private PermissionFeign permissionFeign;
    /**
     * 获得角色拥有的资源编号列表
     *
     * @param roleId 角色编号
     * @return 资源编号列表
     */
    public Set<Integer> listRoleResources(Integer roleId) {
        CommonResult<Set<Integer>> listRoleResourceIdsResult = permissionFeign.listRoleResourceIds(roleId);
        listRoleResourceIdsResult.checkError();
        return listRoleResourceIdsResult.getData();
    }

    /**
     * 赋予角色资源
     *
     * @param assignRoleResourceDTO 赋予角色资源 DTO
     */
    public void assignRoleResource(PermissionAssignRoleResourceDTO assignRoleResourceDTO) {
        CommonResult<Boolean> assignRoleResourceResult = permissionFeign.assignRoleResource(
                PermissionConvert.INSTANCE.convert(assignRoleResourceDTO));
        assignRoleResourceResult.checkError();
    }

    /**
     * 赋予用户角色
     *
     * @param assignAdminRoleDTO 赋予用户角色 DTO
     */
    public void assignAdminRole(PermissionAssignAdminRoleDTO assignAdminRoleDTO) {
        CommonResult<Boolean> assignAdminRoleResult = permissionFeign.assignAdminRole(
                PermissionConvert.INSTANCE.convert(assignAdminRoleDTO));
        assignAdminRoleResult.checkError();
    }

    /**
     * 获得用户拥有的角色编号列表
     *
     * @param adminId 管理员编号
     * @return 角色编号列表
     */
    public Set<Integer> listAdminRoles(Integer adminId) {
        CommonResult<Set<Integer>> listAdminRoleIdsResult = permissionFeign.listAdminRoleIds(adminId);
        listAdminRoleIdsResult.checkError();
        return listAdminRoleIdsResult.getData();
    }

}
