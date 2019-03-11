package cn.iocoder.mall.pay.application.controller;

import cn.iocoder.mall.pay.api.PayService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Reference(validation = "true")
    private PayService payService;

}
