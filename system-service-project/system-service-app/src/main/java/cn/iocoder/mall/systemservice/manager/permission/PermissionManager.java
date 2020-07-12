package cn.iocoder.mall.systemservice.manager.permission;

import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignRoleResourceDTO;
import cn.iocoder.mall.systemservice.service.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 权限 Manager
 */
@Service
public class PermissionManager {

    @Autowired
    private PermissionService permissionService;

    /**
     * 获得角色拥有的资源编号
     *
     * @param roleId 角色编号
     * @return 资源编号列表
     */
    public Set<Integer> listRoleResourceId(Integer roleId) {
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
