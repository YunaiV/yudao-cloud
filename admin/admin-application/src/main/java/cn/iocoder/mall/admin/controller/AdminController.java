package cn.iocoder.mall.admin.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import cn.iocoder.mall.admin.convert.AdminConvert;
import cn.iocoder.mall.admin.vo.AdminInfoVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/admin")
@Api("管理员模块")
public class AdminController {

    @GetMapping("/info")
    public CommonResult<AdminInfoVO> info() {
        return CommonResult.success(AdminConvert.INSTANCE.convert(AdminSecurityContextHolder.getContext()));
    }

}