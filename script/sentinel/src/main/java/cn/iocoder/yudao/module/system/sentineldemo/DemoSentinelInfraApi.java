package cn.iocoder.yudao.module.system.sentineldemo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.infra.enums.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Sentinel 示例：调用 infra-server 示例 RPC 接口的 Feign 客户端
 *
 * 1. 显式配置 fallbackFactory：调用失败，或者被 Sentinel 限流、熔断时，由 DemoSentinelInfraApiFallbackFactory 返回 CommonResult 错误码。
 *    项目其它的 Feign 客户端没有配置，保持原有的行为：失败时抛出原始的异常
 * 2. 开启 feign.sentinel.enabled 后，每个方法是一个 Sentinel 资源，资源名是「HTTP 方法:http://服务名 + 路径」，
 *    echo 方法是 GET:http://infra-server/rpc-api/infra/sentinel-demo/echo，与熔断规则中的 resource 一致
 *
 * 示例为了不修改 yudao-module-infra-api 模块，将接口放在调用方；自己的业务，建议和项目其它 RPC 一样，放到被调用方的 -api 模块
 *
 * @author 芋道源码
 */
@FeignClient(name = ApiConstants.NAME, contextId = "demoSentinelInfraApi", // 独立的 contextId，避免与 infra-server 已有的 Feign 客户端冲突
        fallbackFactory = DemoSentinelInfraApiFallbackFactory.class)
public interface DemoSentinelInfraApi {

    /**
     * 与 infra-server 中 DemoSentinelRpcController 的 PREFIX 保持一致
     */
    String PREFIX = ApiConstants.PREFIX + "/sentinel-demo";

    /**
     * 示例调用
     *
     * @param mode infra-server 的处理方式：ok 正常返回；slow 慢调用，约 500 毫秒；biz-error 返回业务错误码
     * @return 处理方式
     */
    @GetMapping(PREFIX + "/echo")
    CommonResult<String> echo(@RequestParam("mode") String mode);

}
