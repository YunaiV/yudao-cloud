package cn.iocoder.mall.systemservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.manager.permission.PermissionManager;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignAdminRoleDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignRoleResourceDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionCheckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/11
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController {
    @Autowired
    private PermissionManager permissionManager;

    @GetMapping("listRoleResourceIds")
    public CommonResult<Set<Integer>> listRoleResourceIds(@RequestParam("roleId")Integer roleId) {
        return success(permissionManager.listRoleResourceIds(roleId));
    }

    @PostMapping("assignRoleResource")
    public CommonResult<Boolean> assignRoleResource(@RequestBody PermissionAssignRoleResourceDTO assignRoleResourceDTO) {
        permissionManager.assignRoleResource(assignRoleResourceDTO);
        return success(true);
    }

    @GetMapping("listAdminRoleIds")
    public CommonResult<Set<Integer>> listAdminRoleIds(@RequestParam("adminId")Integer adminId) {
        return success(permissionManager.listAdminRoleIds(adminId));
    }

    @GetMapping("mapAdminRoleIds")
    public CommonResult<Map<Integer, Set<Integer>>> mapAdminRoleIds(@RequestParam("adminIds") Collection<Integer> adminIds) {
        return success(permissionManager.mapAdminRoleIds(adminIds));
    }

    @PostMapping("assignAdminRole")
    public CommonResult<Boolean> assignAdminRole(@RequestBody PermissionAssignAdminRoleDTO assignAdminRoleDTO) {
        permissionManager.assignAdminRole(assignAdminRoleDTO);
        return success(true);
    }

    @PostMapping("checkPermission")
    public CommonResult<Boolean> checkPermission(@RequestBody PermissionCheckDTO checkDTO) {
        permissionManager.checkPermission(checkDTO);
        return success(true);
    }
}
