package cn.iocoder.mall.systemservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.manager.permission.RoleManager;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RolePageDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RoleUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
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
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleManager roleManager;

    @PostMapping("createRole")
    public CommonResult<Integer> createRole(@RequestBody RoleCreateDTO createDTO) {
        return success(roleManager.createRole(createDTO));
    }

    @PostMapping("updateRole")
    public CommonResult<Boolean> updateRole(@RequestBody RoleUpdateDTO updateDTO) {
        roleManager.updateRole(updateDTO);
        return success(true);
    }

    @GetMapping("deleteRole")
    public CommonResult<Boolean> deleteRole(@RequestParam("roleId")Integer roleId) {
        roleManager.deleteRole(roleId);
        return success(true);
    }

    @GetMapping("getRole")
    public CommonResult<RoleVO> getRole(@RequestParam("roleId")Integer roleId) {
        return success(roleManager.getRole(roleId));
    }

    @GetMapping("listAllRoles")
    public CommonResult<List<RoleVO>> listAllRoles() {
        return success(roleManager.listAllRoles());
    }

    @GetMapping("listRoles")
    public CommonResult<List<RoleVO>> listRoles(@RequestParam("roleIds")Collection<Integer> roleIds) {
        return success(roleManager.listRoles(roleIds));
    }

    @PostMapping("pageRole")
    public CommonResult<PageResult<RoleVO>> pageRole(@RequestBody RolePageDTO pageDTO) {
        return success(roleManager.pageRole(pageDTO));
    }

    @GetMapping("listAdminRoleIds")
    public CommonResult<Set<Integer>> listAdminRoleIds(@RequestParam("adminId") Integer adminId) {
        return success(roleManager.listAdminRoleIds(adminId));
    }

}
