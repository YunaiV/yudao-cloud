package cn.iocoder.mall.pay.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.PayTransactionService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/transaction") // TODO 芋艿，理论来说，是用户无关的。这里先酱紫先~
public class PayTransactionController {

    @Reference(validation = "true")
    private PayTransactionService payService;

    @PostMapping("/submit") // TODO api 注释
    public CommonResult submit() { // TODO 1. params 2. result
        return null;
    }

}