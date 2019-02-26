package cn.iocoder.mall.admin.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/admin")
@Api("管理员模块")
public class AdminController {

    @GetMapping("/info")
    public CommonResult<Void> info() {
        return null;
    }

}