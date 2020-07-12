package cn.iocoder.mall.managementweb.manager.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.permission.dto.PermissionAssignRoleResourceDTO;
import cn.iocoder.mall.managementweb.convert.permission.PermissionConvert;
import cn.iocoder.mall.systemservice.rpc.permission.PermissionRpc;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 权限 Manager
 */
@Service
public class PermissionManager {

    @Reference(version = "${dubbo.consumer.PermissionRpc.version}", validation = "false")
    private PermissionRpc permissionRpc;

    /**
     * 获得角色拥有的资源编号
     *
     * @param roleId 角色编号
     * @return 资源编号列表
     */
    public Set<Integer> listRoleResource(Integer roleId) {
        CommonResult<Set<Integer>> listAdminRoleIdsResult = permissionRpc.listRoleResourceId(roleId);
        listAdminRoleIdsResult.checkError();
        return listAdminRoleIdsResult.getData();
    }

    /**
     * 赋予角色资源
     *
     * @param assignRoleResourceDTO 赋予角色资源 DTO
     */
    public void assignRoleResource(PermissionAssignRoleResourceDTO assignRoleResourceDTO) {
        CommonResult<Boolean> assignRoleResourceResult = permissionRpc.assignRoleResource(
                PermissionConvert.INSTANCE.convert(assignRoleResourceDTO));
        assignRoleResourceResult.checkError();
    }

}
