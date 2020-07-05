package cn.iocoder.mall.managementweb.controller.admin;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateInfoDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateStatusDTO;
import cn.iocoder.mall.managementweb.manager.admin.AdminManager;
import cn.iocoder.mall.security.admin.core.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api("管理员 API")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminManager adminManager;

    // =========== 管理员管理 API ===========
//    @GetMapping("/page")
//    @RequiresPermissions("system.admin.page")
//    @ApiOperation(value = "管理员分页")
//    public CommonResult<PageResult<AdminVO>> page(AdminPageDTO adminPageDTO) {
//        PageResult<AdminBO> page = adminService.getAdminPage(adminPageDTO);
//        PageResult<AdminVO> resultPage = AdminConvert.INSTANCE.convertAdminVOPage(page);
//        // 拼接结果
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
//        return success(resultPage);
//    }

    @ApiOperation(value = "创建管理员")
    @PostMapping("/create")
    public CommonResult<Integer> createAdmin(AdminCreateDTO createDTO, HttpServletRequest request) {
        return success(adminManager.createAdmin(createDTO, AdminSecurityContextHolder.getAdminId(), HttpUtil.getIp(request)));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新管理员")
    public CommonResult<Boolean> updateAdmin(AdminUpdateInfoDTO updateInfoDTO) {
        adminManager.updateAdmin(updateInfoDTO);
        return success(true);
    }

    @PostMapping("/update_status")
    @ApiOperation(value = "更新管理员状态")
    public CommonResult<Boolean> updateUserStatus(AdminUpdateStatusDTO updateStatusDTO) {
        adminManager.updateAdminStatus(updateStatusDTO);
        return success(true);
    }

}
