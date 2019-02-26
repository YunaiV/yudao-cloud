package cn.iocoder.mall.user.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import cn.iocoder.mall.user.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api("用户模块")
public class UserController {

    @GetMapping("/info")
    @ApiOperation(value = "用户信息")
    public CommonResult<UserInfoVO> info() {
        // TODO 芋艿，正在实现中
        UserInfoVO user = new UserInfoVO().setId(UserSecurityContextHolder.getContext().getUid());
        return CommonResult.success(user);
    }

}