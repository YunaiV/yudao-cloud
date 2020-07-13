package cn.iocoder.mall.managementweb.controller.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.permission.dto.PermissionAssignAdminRoleDTO;
import cn.iocoder.mall.managementweb.controller.permission.dto.PermissionAssignRoleResourceDTO;
import cn.iocoder.mall.managementweb.manager.permission.PermissionManager;
import cn.iocoder.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * 权限 Controller
 */
@RestController
@RequestMapping("/permission")
@Api(tags = "权限")
@Validated
public class PermissionController {

    @Autowired
    private PermissionManager permissionManager;

    @GetMapping("/list-role-resources")
    @ApiOperation("获得角色拥有的资源编号")
    @ApiImplicitParam(name = "roleId", value = "角色编号", required = true)
    @RequiresPermissions("system:permission:assign-role-resource")
    public CommonResult<Set<Integer>> listRoleResources(Integer roleId) {
        return success(permissionManager.listRoleResources(roleId));
    }

    @PostMapping("/assign-role-resource")
    @ApiOperation("赋予角色资源")
    @RequiresPermissions("system:permission:assign-role-resource")
    public CommonResult<Boolean> assignRoleResource(PermissionAssignRoleResourceDTO assignRoleResourceDTO) {
        permissionManager.assignRoleResource(assignRoleResourceDTO);
        return success(true);
    }

    @GetMapping("/list-admin-roles")
    @ApiOperation("获得管理员拥有的角色编号列表")
    @RequiresPermissions("system:permission:assign-admin-role")
    @ApiImplicitParam(name = "adminId", value = "管理员编号", required = true)
    public CommonResult<Set<Integer>> listAdminRoles(Integer adminId) {
        return success(permissionManager.listAdminRoles(adminId));
    }

    @PostMapping("/assign-admin-role")
    @ApiOperation("赋予用户角色")
    @RequiresPermissions("system:permission:assign-admin-role")
    public CommonResult<Boolean> assignAdminRole(PermissionAssignAdminRoleDTO assignAdminRoleDTO) {
        permissionManager.assignAdminRole(assignAdminRoleDTO);
        return success(true);
    }

}
