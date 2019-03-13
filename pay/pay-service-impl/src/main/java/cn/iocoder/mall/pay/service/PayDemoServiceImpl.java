package cn.iocoder.mall.pay.service;

import cn.iocoder.mall.pay.api.PayDemoService;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class PayDemoServiceImpl implements PayDemoService {

    @Override
    public String updatePaySuccess(String orderId) {
        return "你好呀";
    }

}