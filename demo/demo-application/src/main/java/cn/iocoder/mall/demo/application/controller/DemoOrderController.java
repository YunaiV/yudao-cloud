package cn.iocoder.mall.demo.application.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.demo.application.convert.DemoOrderConvert;
import cn.iocoder.mall.demo.application.dto.DemoOrderAddDTO;
import cn.iocoder.mall.demo.business.api.DemoOrderService;
import cn.iocoder.mall.demo.business.bo.order.DemoOrderAddBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class DemoOrderController {

    @Autowired
    private DemoOrderService demoOrderService;

    @PostMapping("/add")
    public CommonResult<Integer> add(DemoOrderAddDTO addDTO) {
        DemoOrderAddBO addBO = DemoOrderConvert.INSTANCE.convert(addDTO);
        addBO.setUserId(10); // TODO 10 用户编号。
        Integer orderId = demoOrderService.add(addBO);
        return CommonResult.success(orderId);
    }

}
