package cn.iocoder.mall.system.rest.controller.authorization;

import cn.iocoder.common.framework.enums.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.security.core.annotation.RequiresPermissions;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleAddDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleDeleteDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RolePageDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleUpdateDTO;
import cn.iocoder.mall.system.biz.service.authorization.RoleService;
import cn.iocoder.mall.system.rest.convert.authorization.AdminsRoleConvert;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRoleAddRequest;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRolePageRequest;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRoleUpdateRequest;
import cn.iocoder.mall.system.rest.response.authorization.AdminsRolePageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/role")
@Api(tags = "管理员 - 角色 API")
public class AdminsRoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/page")
    @ApiOperation(value = "角色分页")
    @RequiresPermissions("system:role:page")
    public CommonResult<PageResult<AdminsRolePageResponse>> page(AdminsRolePageRequest request) {
        RolePageDTO pageDTO = AdminsRoleConvert.INSTANCE.convert(request);
        PageResult<RoleBO> pageResult = roleService.getRolePage(pageDTO);
        return CommonResult.success(AdminsRoleConvert.INSTANCE.convertPage(pageResult));
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建角色")
    @RequiresPermissions("system:role:add")
    public CommonResult<Integer> add(AdminsRoleAddRequest request) {
        RoleAddDTO addDTO = AdminsRoleConvert.INSTANCE.convert(request)
                .setAdminId(AdminSecurityContextHolder.getAdminId());
        return CommonResult.success(roleService.addRole(addDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新角色")
    @RequiresPermissions("system:role:update")
    public CommonResult<Boolean> update(AdminsRoleUpdateRequest request) {
        RoleUpdateDTO updateDTO = AdminsRoleConvert.INSTANCE.convert(request)
                .setAdminId(AdminSecurityContextHolder.getAdminId());
        roleService.updateRole(updateDTO);
        return CommonResult.success(true);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除角色")
    @RequiresPermissions("system:role:delete")
    @ApiImplicitParam(name = "id", value = "角色编号", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        RoleDeleteDTO deleteDTO = new RoleDeleteDTO().setId(id)
                .setAdminId(AdminSecurityContextHolder.getAdminId());
        roleService.deleteRole(deleteDTO);
        return CommonResult.success(true);
    }




}
