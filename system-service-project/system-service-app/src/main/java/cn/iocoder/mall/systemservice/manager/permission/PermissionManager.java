package cn.iocoder.mall.systemservice.manager.permission;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.common.framework.exception.GlobalException;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignAdminRoleDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignRoleResourceDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionCheckDTO;
import cn.iocoder.mall.systemservice.service.permission.PermissionService;
import cn.iocoder.mall.systemservice.service.permission.ResourceService;
import cn.iocoder.mall.systemservice.service.permission.RoleService;
import cn.iocoder.mall.systemservice.service.permission.bo.ResourceBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static cn.iocoder.common.framework.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;

/**
 * 权限 Manager
 */
@Service
public class PermissionManager {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    /**
     * 获得角色拥有的资源编号
     *
     * @param roleId 角色编号
     * @return 资源编号列表
     */
    public Set<Integer> listRoleResourceIds(Integer roleId) {
        // 超级管理员，拥有所有资源
        if (roleService.hasSuperAdmin(Collections.singleton(roleId))) {
            return CollectionUtils.convertSet(resourceService.listResources(), ResourceBO::getId);
        }
        // 非超级管理员，查询拥有资源
        return permissionService.listRoleResourceIds(roleId);
    }

    /**
     * 赋予角色资源
     *
     * @param assignResourceDTO 赋予角色资源 DTO
     */
    public void assignRoleResource(PermissionAssignRoleResourceDTO assignResourceDTO) {
        permissionService.assignRoleResource(assignResourceDTO.getRoleId(), assignResourceDTO.getResourceIds());
    }

    /**
     * 获得管理员拥有的角色编号列表
     *
     * @param adminId 管理员编号
     * @return 角色编号列表
     */
    public Set<Integer> listAdminRoleIds(Integer adminId) {
        return permissionService.listAdminRoleIds(adminId);
    }

    /**
     * 获得每个管理员拥有的角色编号
     * 返回的结果，key 为管理员编号
     *
     * @param adminIds 管理员编号列表
     * @return 每个管理员拥有的角色编号
     */
    public Map<Integer, Set<Integer>> mapAdminRoleIds(Collection<Integer> adminIds) {
        return permissionService.mapAdminRoleIds(adminIds);
    }

    /**
     * 赋予管理员角色
     *
     * @param assignAdminRoleDTO 赋予管理员角色 DTO
     */
    public void assignAdminRole(PermissionAssignAdminRoleDTO assignAdminRoleDTO) {
        permissionService.assignAdminRole(assignAdminRoleDTO.getAdminId(), assignAdminRoleDTO.getRoleIds());
    }

    /**
     * 校验管理员是否拥有指定权限。
     *
     * 如果没有，则抛出 {@link cn.iocoder.common.framework.exception.ServiceException} 异常
     *
     * @param checkDTO 校验权限 DTO
     */
    public void checkPermission(PermissionCheckDTO checkDTO) {
        // 查询管理员拥有的角色关联数据
        Set<Integer> roleIds = permissionService.listAdminRoleIds(checkDTO.getAdminId());
        if (CollectionUtil.isEmpty(roleIds)) { // 如果没有角色，默认无法访问
            throw new GlobalException(FORBIDDEN);
        }
        // 判断是否为超管。若是超管，默认有所有权限
        if (roleService.hasSuperAdmin(roleIds)) {
            return;
        }
        // 校验权限
        permissionService.checkPermission(roleIds, checkDTO.getPermissions());
    }

}
