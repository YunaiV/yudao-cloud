package cn.iocoder.mall.managementweb.controller.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.permission.dto.PermissionAssignRoleResourceDTO;
import cn.iocoder.mall.managementweb.manager.permission.PermissionManager;
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

    @GetMapping("/list-role-resource")
    @ApiOperation("获得角色拥有的资源编号")
    @ApiImplicitParam(name = "roleId", value = "角色编号", required = true)
    public CommonResult<Set<Integer>> listRoleResource(Integer roleId) {
        return success(permissionManager.listRoleResource(roleId));
    }

    @PostMapping("/assign-role-resource")
    @ApiOperation("赋予角色资源")
    public CommonResult<Boolean> assignRoleResource(PermissionAssignRoleResourceDTO assignRoleResourceDTO) {
        permissionManager.assignRoleResource(assignRoleResourceDTO);
        return success(true);
    }

}
