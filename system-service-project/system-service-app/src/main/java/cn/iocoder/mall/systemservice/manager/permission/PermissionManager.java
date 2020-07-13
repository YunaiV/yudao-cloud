package cn.iocoder.mall.systemservice.manager.permission;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignRoleResourceDTO;
import cn.iocoder.mall.systemservice.service.permission.PermissionService;
import cn.iocoder.mall.systemservice.service.permission.ResourceService;
import cn.iocoder.mall.systemservice.service.permission.RoleService;
import cn.iocoder.mall.systemservice.service.permission.bo.ResourceBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

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
    public Set<Integer> listRoleResourceId(Integer roleId) {
        // 超级管理员，拥有所有资源
        if (roleService.hasSuperAdmin(Collections.singleton(roleId))) {
            return CollectionUtils.convertSet(resourceService.listResource(), ResourceBO::getId);
        }
        // 非超级管理员，查询拥有资源
        return permissionService.listRoleResourceId(roleId);
    }

    /**
     * 赋予角色资源
     *
     * @param assignResourceDTO 赋予角色资源 DTO
     */
    public void assignRoleResource(PermissionAssignRoleResourceDTO assignResourceDTO) {
        permissionService.assignRoleResource(assignResourceDTO.getRoleId(), assignResourceDTO.getResourceIds());
    }

}
