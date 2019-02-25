package cn.iocoder.mall.user.controller;

import cn.iocoder.mall.user.sdk.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public Long info() {
        // TODO 芋艿，正在实现中
        return SecurityContextHolder.getContext().getUid();
    }

}