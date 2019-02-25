package cn.iocoder.mall.user.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.sdk.context.SecurityContextHolder;
import cn.iocoder.mall.user.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public CommonResult<UserVO> info() {
        // TODO 芋艿，正在实现中
        UserVO user = new UserVO().setId(SecurityContextHolder.getContext().getUid());
        return CommonResult.success(user);
    }

}