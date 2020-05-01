package cn.iocoder.mall.system.rest.controller.admin;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.security.core.annotation.RequiresPermissions;
import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.dto.admin.AdminPageDTO;
import cn.iocoder.mall.system.biz.service.admin.AdminService;
import cn.iocoder.mall.system.rest.convert.admin.AdminsAdminConvert;
import cn.iocoder.mall.system.rest.request.admin.AdminsAdminPageRequest;
import cn.iocoder.mall.system.rest.response.admin.AdminsAdminPageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/admin")
@Api(tags = "管理员 - 管理员 API")
public class AdminsAdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/page")
    @RequiresPermissions("system:admin:page")
    @ApiOperation(value = "管理员分页")
    public CommonResult<PageResult<AdminsAdminPageResponse>> page(AdminsAdminPageRequest request) {
        // 查询管理员分页
        AdminPageDTO pageDTO = AdminsAdminConvert.INSTANCE.convert(request);
        PageResult<AdminBO> adminPageBO = adminService.getAdminPage(pageDTO);
        PageResult<AdminsAdminPageResponse> adminPageResponse = AdminsAdminConvert.INSTANCE.convertPage(adminPageBO);
        if (adminPageResponse.getList().isEmpty()) {
            return CommonResult.success(adminPageResponse);
        }
        // 拼接角色数据


        // TODO 拼接部门数据

        // 拼接结果
//        if (!resultPage.getList().isEmpty()) {
//            // 查询角色数组
//            Map<Integer, Collection<RoleBO>> roleMap = adminService.getAdminRolesMap(CollectionUtil.convertList(resultPage.getList(), AdminBO::getId));
//            resultPage.getList().forEach(admin -> admin.setRoles(AdminConvert.INSTANCE.convertAdminVORoleList(roleMap.get(admin.getId()))));
//
//            // 查询对应部门
//            List<DeptmentBO> deptmentBOS =  deptmentService.getAllDeptments();
//            Map<Integer, String> deptNameMap = deptmentBOS.stream().collect(Collectors.toMap(d->d.getId(), d->d.getName()));
//            //管理员所在部门被删后，变成未分配状态
//            deptNameMap.put(0, "未分配");
//            resultPage.getList().forEach(admin->{
//                admin.setDeptment(new AdminVO.Deptment(admin.getDeptmentId(), deptNameMap.get(admin.getDeptmentId())));
//            });
//        }

        return CommonResult.success(adminPageResponse);
    }

//    @PostMapping("/add")
//    @ApiOperation(value = "创建管理员")
//    public CommonResult<AdminBO> add(AdminAddDTO adminAddDTO) {
//        return success(adminService.addAdmin(AdminSecurityContextHolder.getContext().getAdminId(), adminAddDTO));
//    }
//
//    @PostMapping("/update")
//    @ApiOperation(value = "更新管理员")
//    public CommonResult<Boolean> update(AdminUpdateDTO adminUpdateDTO) {
//        return success(adminService.updateAdmin(AdminSecurityContextHolder.getContext().getAdminId(), adminUpdateDTO));
//    }
//
//    @PostMapping("/update_status")
//    @ApiOperation(value = "更新管理员状态")
//    public CommonResult<Boolean> updateStatus(AdminUpdateStatusDTO adminUpdateStatusDTO) {
//        return success(adminService.updateAdminStatus(AdminSecurityContextHolder.getContext().getAdminId(), adminUpdateStatusDTO));
//    }
//
//    @PostMapping("/delete")
//    @ApiOperation(value = "删除管理员")
//    @ApiImplicitParam(name = "id", value = "管理员编号", required = true, example = "1")
//    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
//        return success(adminService.deleteAdmin(AdminSecurityContextHolder.getContext().getAdminId(), id));
//    }
//
//    @GetMapping("/role_list")
//    @ApiOperation(value = "指定管理员拥有的角色列表")
//    @ApiImplicitParam(name = "id", value = "管理员编号", required = true, example = "1")
//    public CommonResult<List<AdminRoleVO>> roleList(@RequestParam("id") Integer id) {
//        // 获得所有角色列表
//        List<RoleBO> allRoleList = roleService.getRoleList();
//        // 获得管理员的角色数组
//        Set<Integer> adminRoleIdSet = CollectionUtil.convertSet(adminService.getRoleList(id), RoleBO::getId);
//        // 转换出返回结果
//        List<AdminRoleVO> result = AdminConvert.INSTANCE.convert(allRoleList);
//        // 设置每个角色是否赋予给改管理员
//        result.forEach(adminRoleVO -> adminRoleVO.setAssigned(adminRoleIdSet.contains(adminRoleVO.getId())));
//        return success(result);
//    }
//
//    @PostMapping("/assign_role")
//    @ApiOperation(value = "分配给管理员角色")
//    public CommonResult<Boolean> assignRole(AdminAssignRoleDTO adminAssignRoleDTO) {
//        return success(adminService.assignAdminRole(AdminSecurityContextHolder.getContext().getAdminId(), adminAssignRoleDTO));
//    }

}
