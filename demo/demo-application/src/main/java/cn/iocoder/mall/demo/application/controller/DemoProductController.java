package cn.iocoder.mall.demo.application.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.demo.application.convert.DemoProductConvert;
import cn.iocoder.mall.demo.application.vo.DemoProductVO;
import cn.iocoder.mall.demo.business.api.DemoProductService;
import cn.iocoder.mall.demo.business.bo.product.DemoProductBO;
import cn.iocoder.mall.demo.rpc.api.DemoProductRpcService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class DemoProductController {

    @Autowired
    private DemoProductService productService;

    @Reference(validation = "true", version = "${dubbo.consumer.DemoProductRpcService.version}")
    private DemoProductRpcService productRpcService;

    @GetMapping("/get")
    public CommonResult<DemoProductVO> get(@RequestParam("id") Integer id) {
        DemoProductBO product = productService.get(id);
        return CommonResult.success(DemoProductConvert.INSTANCE.convert(product));
    }

    // TODO 芋艿，这里只是做一个 demo 。实际一般不会这么玩，更多是内嵌的，像 {@link #get(Integer id)} 的情况。
    @GetMapping("/get2")
    public CommonResult<DemoProductVO> get2(@RequestParam("id") Integer id) {
        cn.iocoder.mall.demo.rpc.vo.DemoProductVO product = productRpcService.get(id);
        return null;
    }

}
