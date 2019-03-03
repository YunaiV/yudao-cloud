package cn.iocoder.mall.admin.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.OAuth2Service;
import cn.iocoder.mall.admin.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.application.convert.AdminConvert;
import cn.iocoder.mall.admin.application.convert.PassportConvert;
import cn.iocoder.mall.admin.application.vo.AdminInfoVO;
import cn.iocoder.mall.admin.application.vo.PassportLoginVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admins/passport")
@Api("Admin Passport 模块")
public class PassportController {

    @Reference
    private OAuth2Service oauth2Service;

    @PostMapping("/login")
    @ApiOperation(value = "手机号 + 密码登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, example = "15601691300"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, example = "future")
    })
    public CommonResult<PassportLoginVO> login(@RequestParam("username") String username,
                                               @RequestParam("password") String password) {
        CommonResult<OAuth2AccessTokenBO> result = oauth2Service.getAccessToken(username, password);
        return PassportConvert.INSTANCE.convert(result);
    }

    // TODO 艿艿：后续继续完善
    @GetMapping("/info")
    public CommonResult<AdminInfoVO> info() {
        return CommonResult.success(AdminConvert.INSTANCE.convert(AdminSecurityContextHolder.getContext()));
    }

}