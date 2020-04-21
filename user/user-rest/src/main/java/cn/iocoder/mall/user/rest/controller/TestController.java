package cn.iocoder.mall.user.rest.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.web.core.constant.CommonMallConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommonMallConstants.ROOT_PATH_USER + "/test")
public class TestController {

    @GetMapping("/echo")
    public CommonResult<Boolean> echo() {
        return CommonResult.success(true);
    }

}
