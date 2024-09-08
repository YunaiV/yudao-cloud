package cn.iocoder.yudao.module.kafka.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * @author zyh
 * @ClassName: UserKafkaTestController
 * @Package cn.iocoder.yudao.module.kafka.controller.app
 * @Description TODO
 * @date 2024/9/4 16:17
 */
@Tag(name = "用户 User - Test")
@RestController
@RequestMapping("/kafka/test")
@Validated
public class UserKafkaTestController {
    @GetMapping("/get")
    @Operation(summary = "获取 test 信息")
    public CommonResult<String> get() {
        System.out.println("user-api");
     return success("true");
    }
}
