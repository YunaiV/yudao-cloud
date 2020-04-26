package cn.iocoder.mall.system.application.controller.admins;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.api.AdminService;
import cn.iocoder.mall.system.api.DeptmentService;
import cn.iocoder.mall.system.api.ResourceService;
import cn.iocoder.mall.system.api.RoleService;
import cn.iocoder.mall.system.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.system.api.bo.resource.ResourceBO;
import cn.iocoder.mall.system.api.bo.role.RoleBO;
import cn.iocoder.mall.system.api.bo.admin.AdminBO;
import cn.iocoder.mall.system.api.constant.ResourceConstants;
import cn.iocoder.mall.system.api.dto.admin.*;
import cn.iocoder.mall.system.application.convert.AdminConvert;
import cn.iocoder.mall.system.application.convert.ResourceConvert;
import cn.iocoder.mall.system.application.vo.admin.AdminMenuTreeNodeVO;
import cn.iocoder.mall.system.application.vo.admin.AdminRoleVO;
import cn.iocoder.mall.system.application.vo.admin.AdminVO;
import cn.iocoder.mall.system.sdk.annotation.RequiresPermissions;
import cn.iocoder.mall.system.sdk.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/admin")
@Api("管理员模块")
public class AdminController {

    @Reference(validation = "true", version = "${dubbo.provider.ResourceService.version}")
    private ResourceService resourceService;

    @Reference(validation = "true", version = "${dubbo.provider.AdminService.version}")
    private AdminService adminService;

    @Reference(validation = "true", version = "${dubbo.provider.RoleService.version}")
    private RoleService roleService;

    @Autowired
    private DeptmentService deptmentService;

    // =========== 管理员管理 API ===========
    //TODO 目前需要增加搜索所有子部门的用户
    @GetMapping("/page")
    @RequiresPermissions("system.admin.page")
    @ApiOperation(value = "管理员分页")
    public CommonResult<PageResult<AdminVO>> page(AdminPageDTO adminPageDTO) {
        PageResult<AdminBO> page = adminService.getAdminPage(adminPageDTO);
        PageResult<AdminVO> resultPage = AdminConvert.INSTANCE.convertAdminVOPage(page);
        // 拼接结果
        if (!resultPage.getList().isEmpty()) {
            // 查询角色数组
            Map<Integer, Collection<RoleBO>> roleMap = adminService.getAdminRolesMap(CollectionUtil.convertList(resultPage.getList(), AdminBO::getId));
            resultPage.getList().forEach(admin -> admin.setRoles(AdminConvert.INSTANCE.convertAdminVORoleList(roleMap.get(admin.getId()))));

            // 查询对应部门
            List<DeptmentBO> deptmentBOS =  deptmentService.getAllDeptments();
            Map<Integer, String> deptNameMap = deptmentBOS.stream().collect(Collectors.toMap(d->d.getId(), d->d.getName()));
            //管理员所在部门被删后，变成未分配状态
            deptNameMap.put(0, "未分配");
            resultPage.getList().forEach(admin->{
                admin.setDeptment(new AdminVO.Deptment(admin.getDeptmentId(), deptNameMap.get(admin.getDeptmentId())));
            });
        }

        return success(resultPage);
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建管理员")
    public CommonResult<AdminBO> add(AdminAddDTO adminAddDTO) {
        return success(adminService.addAdmin(AdminSecurityContextHolder.getContext().getAdminId(), adminAddDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新管理员")
    public CommonResult<Boolean> update(AdminUpdateDTO adminUpdateDTO) {
        return success(adminService.updateAdmin(AdminSecurityContextHolder.getContext().getAdminId(), adminUpdateDTO));
    }

    @PostMapping("/update_status")
    @ApiOperation(value = "更新管理员状态")
    public CommonResult<Boolean> updateStatus(AdminUpdateStatusDTO adminUpdateStatusDTO) {
        return success(adminService.updateAdminStatus(AdminSecurityContextHolder.getContext().getAdminId(), adminUpdateStatusDTO));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除管理员")
    @ApiImplicitParam(name = "id", value = "管理员编号", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        return success(adminService.deleteAdmin(AdminSecurityContextHolder.getContext().getAdminId(), id));
    }

    @GetMapping("/role_list")
    @ApiOperation(value = "指定管理员拥有的角色列表")
    @ApiImplicitParam(name = "id", value = "管理员编号", required = true, example = "1")
    public CommonResult<List<AdminRoleVO>> roleList(@RequestParam("id") Integer id) {
        // 获得所有角色列表
        List<RoleBO> allRoleList = roleService.getRoleList();
        // 获得管理员的角色数组
        Set<Integer> adminRoleIdSet = CollectionUtil.convertSet(adminService.getRoleList(id), RoleBO::getId);
        // 转换出返回结果
        List<AdminRoleVO> result = AdminConvert.INSTANCE.convert(allRoleList);
        // 设置每个角色是否赋予给改管理员
        result.forEach(adminRoleVO -> adminRoleVO.setAssigned(adminRoleIdSet.contains(adminRoleVO.getId())));
        return success(result);
    }

    @PostMapping("/assign_role")
    @ApiOperation(value = "分配给管理员角色")
    public CommonResult<Boolean> assignRole(AdminAssignRoleDTO adminAssignRoleDTO) {
        return success(adminService.assignAdminRole(AdminSecurityContextHolder.getContext().getAdminId(), adminAssignRoleDTO));
    }

}
