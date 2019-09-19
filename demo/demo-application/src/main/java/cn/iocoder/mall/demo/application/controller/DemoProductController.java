package cn.iocoder.mall.demo.application.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.demo.application.convert.DemoProductConvert;
import cn.iocoder.mall.demo.application.vo.DemoProductVO;
import cn.iocoder.mall.demo.business.api.DemoProductService;
import cn.iocoder.mall.demo.business.bo.DemoProductBO;
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

    @GetMapping("/get")
    public CommonResult<DemoProductVO> get(@RequestParam("id") Integer id) {
        DemoProductBO product = productService.get(id);
        return CommonResult.success(DemoProductConvert.INSTANCE.convert(product));
    }

}
