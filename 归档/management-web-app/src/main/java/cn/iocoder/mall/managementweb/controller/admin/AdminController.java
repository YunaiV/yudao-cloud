package cn.iocoder.mall.managementweb.controller.admin;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminPageDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateInfoDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateStatusDTO;
import cn.iocoder.mall.managementweb.controller.admin.vo.AdminPageItemVO;
import cn.iocoder.mall.managementweb.manager.admin.AdminManager;
import cn.iocoder.mall.security.admin.core.context.AdminSecurityContextHolder;
import cn.iocoder.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api(tags = "管理员 API")
@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

    @Autowired
    private AdminManager adminManager;

    @ApiOperation(value = "管理员分页")
    @GetMapping("/page")
    @RequiresPermissions("system:admin:page")
    public CommonResult<PageResult<AdminPageItemVO>> page(AdminPageDTO adminPageDTO) {
        return success(adminManager.pageAdmin(adminPageDTO));
    }

    @ApiOperation(value = "创建管理员")
    @PostMapping("/create")
    @RequiresPermissions("system:admin:create")
    public CommonResult<Integer> createAdmin(AdminCreateDTO createDTO, HttpServletRequest request) {
        return success(adminManager.createAdmin(createDTO, AdminSecurityContextHolder.getAdminId(), HttpUtil.getIp(request)));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新管理员")
    @RequiresPermissions("system:admin:update")
    public CommonResult<Boolean> updateAdmin(AdminUpdateInfoDTO updateInfoDTO) {
        adminManager.updateAdmin(updateInfoDTO);
        return success(true);
    }

    @PostMapping("/update-status")
    @ApiOperation(value = "更新管理员状态")
    @RequiresPermissions("system:admin:update-status")
    public CommonResult<Boolean> updateAdminStatus(@Valid AdminUpdateStatusDTO updateStatusDTO) {
        adminManager.updateAdminStatus(updateStatusDTO);
        return success(true);
    }

}
