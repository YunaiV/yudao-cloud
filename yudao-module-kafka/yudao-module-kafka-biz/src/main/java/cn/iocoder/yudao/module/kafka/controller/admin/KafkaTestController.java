package cn.iocoder.yudao.module.kafka.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyh
 * @ClassName: KafkaTestController
 * @Package cn.iocoder.yudao.module.kafka.controller.admin
 * @Description TODO
 * @date 2024/9/4 16:13
 */
@RestController
@Tag(name = "管理后台 - Test")
@RequestMapping("/kafka/test")
@Validated
public class KafkaTestController {

       @GetMapping("/get")
       @Operation(summary = "获取test信息")
       public CommonResult<String> get(){
           System.out.println("admin-api");
             return CommonResult.success("true");
       }
}
