package cn.iocoder.mall.user.controller;

import org.springframework.web.bind.annotation.GetMapping;

//@RestController
//@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public Long info() {
        // TODO 芋艿，正在实现中
//        return SecurityContextHolder.getContext().getUid();
        return null;
    }

}