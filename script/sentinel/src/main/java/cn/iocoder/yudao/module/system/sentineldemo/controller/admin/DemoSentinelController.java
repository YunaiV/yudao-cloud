package cn.iocoder.yudao.module.system.sentineldemo.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.sentineldemo.DemoSentinelInfraApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - Sentinel 服务保障示例
 *
 * 三个接口分别演示网关限流、服务限流、Feign 熔断降级，对应的规则见 script/sentinel/nacos 目录：
 * 1. /gateway-flow：gateway-server 的 API 分组 system-sentinel-demo-api
 * 2. /flow：system-server 的 URL 资源 /admin-api/system/sentinel-demo/flow
 * 3. /feign：system-server 的 Feign 资源 GET:http://infra-server/rpc-api/infra/sentinel-demo/echo
 *
 * 放在 controller.admin 包下，才会和项目其它管理后台接口一样，添加 /admin-api 前缀
 *
 * @author 芋道源码
 */
@Tag(name = "管理后台 - Sentinel 示例")
@RestController
@RequestMapping("/system/sentinel-demo")
@RequiredArgsConstructor // 使用构造方法注入，避免 JDK 8 与 JDK 17+ 分支的 @Resource 包名不同
public class DemoSentinelController {

    private final DemoSentinelInfraApi demoSentinelInfraApi;

    @GetMapping("/gateway-flow")
    @Operation(summary = "网关限流示例", description = "由 gateway-server 的 API 分组 system-sentinel-demo-api 限流，每秒最多通过 2 个请求")
    public CommonResult<String> gatewayFlow() {
        return success("gateway-flow");
    }

    @GetMapping("/flow")
    @Operation(summary = "服务限流示例", description = "由 system-server 的 URL 资源 /admin-api/system/sentinel-demo/flow 限流，每秒最多通过 2 个请求")
    public CommonResult<String> flow() {
        return success("flow");
    }

    @GetMapping("/feign")
    @Operation(summary = "Feign 熔断降级示例", description = "通过 Feign 调用 infra-server，慢调用比例超过阈值时熔断 10 秒，期间直接返回 fallbackFactory 的错误码")
    @Parameter(name = "mode", description = "infra-server 的处理方式：ok 正常、slow 慢调用、biz-error 业务错误", example = "ok")
    public CommonResult<String> feign(@RequestParam(value = "mode", defaultValue = "ok") String mode) {
        // 必须调用 getCheckedData()：infra-server 的业务错误码、fallbackFactory 返回的错误码，都在这里抛出 ServiceException，
        // 由全局异常处理器返回给前端；不检查时，调用方会把失败当作成功，拿着为 null 的 data 继续执行
        return success(demoSentinelInfraApi.echo(mode).getCheckedData());
    }

}
