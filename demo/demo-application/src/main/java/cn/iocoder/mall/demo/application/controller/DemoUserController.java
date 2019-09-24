package cn.iocoder.mall.demo.application.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.demo.application.convert.DemoUserConvert;
import cn.iocoder.mall.demo.application.vo.DemoUserVO;
import cn.iocoder.mall.demo.rpc.api.DemoUserRpcService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class DemoUserController {

    @Reference(validation = "true", version = "${dubbo.consumer.DemoUserRpcService.version}")
    private DemoUserRpcService userRpcService;

    // TODO 芋艿，这里只是做一个 demo 。实际一般不会这么玩，更多是内嵌的，像 {@link #get(Integer id)} 的情况。
    @GetMapping("/get")
    public CommonResult<DemoUserVO> get(@RequestParam("id") Integer id) {
        cn.iocoder.mall.demo.rpc.vo.DemoUserVO user = userRpcService.get(id);
        return CommonResult.success(DemoUserConvert.INSTANCE.convert(user));
    }

}
