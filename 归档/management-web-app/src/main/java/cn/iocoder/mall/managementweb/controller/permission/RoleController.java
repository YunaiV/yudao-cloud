package cn.iocoder.mall.managementweb.controller.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.permission.dto.RoleCreateDTO;
import cn.iocoder.mall.managementweb.controller.permission.dto.RolePageDTO;
import cn.iocoder.mall.managementweb.controller.permission.dto.RoleUpdateDTO;
import cn.iocoder.mall.managementweb.controller.permission.vo.RoleVO;
import cn.iocoder.mall.managementweb.manager.permission.RoleManager;
import cn.iocoder.mall.security.admin.core.context.AdminSecurityContextHolder;
import cn.iocoder.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 角色 Controller
*/
@RestController
@RequestMapping("/role")
@Api(tags = "角色")
@Validated
public class RoleController {

    @Autowired
    private RoleManager roleManager;

    @PostMapping("/create")
    @ApiOperation("创建角色")
    @RequiresPermissions("system:role:create")
    public CommonResult<Integer> createRole(@Valid RoleCreateDTO createDTO) {
        return success(roleManager.createRole(createDTO, AdminSecurityContextHolder.getAdminId()));
    }

    @PostMapping("/update")
    @ApiOperation("更新角色")
    @RequiresPermissions("system:role:update")
    public CommonResult<Boolean> updateRole(@Valid RoleUpdateDTO updateDTO) {
        roleManager.updateRole(updateDTO);
        return success(true);
    }

    @PostMapping("/delete")
    @ApiOperation("删除角色")
    @ApiImplicitParam(name = "roleId", value = "角色编号", required = true)
    @RequiresPermissions("system:role:delete")
    public CommonResult<Boolean> deleteRole(@RequestParam("roleId") Integer roleId) {
        roleManager.deleteRole(roleId);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得角色")
    @ApiImplicitParam(name = "roleId", value = "角色编号", required = true)
    @RequiresPermissions("system:admin:page")
    public CommonResult<RoleVO> role(@RequestParam("roleId") Integer roleId) {
        return success(roleManager.getRole(roleId));
    }

    @GetMapping("/list-all")
    @ApiOperation("获得所有角色列表")
    @RequiresPermissions("system:role:page")
    public CommonResult<List<RoleVO>> listAllRoles() {
        return success(roleManager.listAllRoles());
    }

    @GetMapping("/list")
    @ApiOperation("获得角色列表")
    @ApiImplicitParam(name = "roleIds", value = "角色编号列表", required = true)
    @RequiresPermissions("system:role:page")
    public CommonResult<List<RoleVO>> listRoles(@RequestParam("roleIds") List<Integer> roleIds) {
        return success(roleManager.listRoles(roleIds));
    }

    @GetMapping("/page")
    @ApiOperation("获得角色分页")
    @RequiresPermissions("system:role:page")
    public CommonResult<PageResult<RoleVO>> pageRole(RolePageDTO pageDTO) {
        return success(roleManager.pageRole(pageDTO));
    }

}
