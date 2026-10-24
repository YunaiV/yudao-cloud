package cn.iocoder.yudao.module.system.sentineldemo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.TOO_MANY_REQUESTS;

/**
 * DemoSentinelInfraApi 的 fallbackFactory：调用 infra-server 失败，或者被 Sentinel 限流、熔断时，返回 CommonResult 错误码
 *
 * 1. 只对 @FeignClient 显式配置了它的 DemoSentinelInfraApi 生效，不影响 system-server 其它的 Feign 客户端
 * 2. infra-server 正常返回的 CommonResult，即使是业务错误码，也不会进入这里：HTTP 状态码是 200，Sentinel 不会计为异常
 * 3. 返回错误码，而不是抛出异常：调用方通过 getCheckedData() 抛出 ServiceException，由全局异常处理器返回给前端。
 *    另外，master 分支的 Spring Cloud Alibaba 2021.0.6.2 会把 fallback 中抛出的异常包装成 AssertionError
 * 4. 返回给前端的提示不包含原始的异常信息，避免泄漏下游的地址、报错等；Sentinel 阻断以外的异常，打印完整的错误日志，便于排查
 *
 * 由 DemoSentinelConfiguration 注册成 Bean，这里不添加 @Component，避免重复注册
 *
 * @author 芋道源码
 */
@Slf4j
public class DemoSentinelInfraApiFallbackFactory implements FallbackFactory<DemoSentinelInfraApi> {

    @Override
    public DemoSentinelInfraApi create(Throwable cause) {
        return new DemoSentinelInfraApi() {

            @Override
            public CommonResult<String> echo(String mode) {
                return buildErrorResult(cause);
            }

        };
    }

    private static <T> CommonResult<T> buildErrorResult(Throwable cause) {
        // 情况一：被 Sentinel 阻断，属于预期内的保护。Sentinel 的 sentinel-block.log 已经按秒汇总记录，这里不打印日志，避免高并发时刷屏
        if (cause instanceof DegradeException) {
            return CommonResult.error(HttpStatus.SERVICE_UNAVAILABLE.value(), "infra-server 暂时不可用，请稍后重试");
        }
        if (cause instanceof BlockException) {
            return CommonResult.error(TOO_MANY_REQUESTS);
        }
        // 情况二：调用失败，例如说超时、连接失败、没有可用的实例、HTTP 状态码不是 2XX 等
        log.error("[buildErrorResult][调用 infra-server 失败]", cause);
        return CommonResult.error(HttpStatus.SERVICE_UNAVAILABLE.value(), "infra-server 暂时不可用，请稍后重试");
    }

}
