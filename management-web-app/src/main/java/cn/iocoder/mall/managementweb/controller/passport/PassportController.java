package cn.iocoder.mall.managementweb.controller.passport;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.passport.dto.PassportLoginDTO;
import cn.iocoder.mall.managementweb.controller.passport.vo.PassportAccessTokenVO;
import cn.iocoder.mall.managementweb.controller.passport.vo.PassportAdminMenuTreeNodeVO;
import cn.iocoder.mall.managementweb.controller.passport.vo.PassportAdminVO;
import cn.iocoder.mall.managementweb.manager.passport.PassportManager;
import cn.iocoder.mall.security.admin.core.context.AdminSecurityContextHolder;
import cn.iocoder.security.annotations.RequiresNone;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Set;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api(tags = "管理员 Passport API")
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private PassportManager passportManager;

    @PostMapping("/login")
    @ApiOperation("账号密码登陆")
    @RequiresNone
    public CommonResult<PassportAccessTokenVO> login(PassportLoginDTO loginDTO,
                                                     HttpServletRequest request) {
        return success(passportManager.login(loginDTO, HttpUtil.getIp(request)));
    }

    @GetMapping("/info")
    @ApiOperation(value = "获得当前管理员信息")
    public CommonResult<PassportAdminVO> getInfo() {
        return success(passportManager.getAdmin(AdminSecurityContextHolder.getAdminId()));
    }

    @GetMapping("/tree-admin-menu")
    @ApiOperation("获得当前管理员的菜单树")
    public CommonResult<List<PassportAdminMenuTreeNodeVO>> treeAdminMenu() {
        return success(passportManager.treeAdminMenu(AdminSecurityContextHolder.getAdminId()));
    }

    @GetMapping("/list-admin-permission")
    @ApiOperation("获得当前管理员的权限列表")
    public CommonResult<Set<String>> listAdminPermission() {
        return success(passportManager.listAdminPermission(AdminSecurityContextHolder.getAdminId()));
    }

}
