package cn.iocoder.yudao.module.system.sentineldemo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.servlet.ServletUtils;
import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.TOO_MANY_REQUESTS;

/**
 * Sentinel 示例的配置类
 *
 * 1. 单独声明示例的 Feign 客户端 DemoSentinelInfraApi 及其 fallbackFactory，不修改 system-server 已有的 RpcConfiguration
 * 2. 当前服务的 URL 资源被 Sentinel 阻断时，返回 HTTP 状态码 + CommonResult 格式的 JSON，与 gateway-server 的格式一致
 *
 * @author 芋道源码
 */
@Configuration(value = "demoSentinelConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = DemoSentinelInfraApi.class)
public class DemoSentinelConfiguration {

    /**
     * DemoSentinelInfraApi 的 fallbackFactory
     *
     * Sentinel 创建 Feign 客户端时，通过 FeignClientFactory 按类型查找它，所以必须注册成 Bean
     */
    @Bean
    public DemoSentinelInfraApiFallbackFactory demoSentinelInfraApiFallbackFactory() {
        return new DemoSentinelInfraApiFallbackFactory();
    }

    /**
     * URL 资源被阻断时的处理器：熔断返回 HTTP 503，限流等其它阻断返回 HTTP 429，替换 Sentinel 默认的 Blocked by Sentinel 文本
     *
     * 对当前服务的【所有】URL 资源生效，不只是示例接口。
     * master 分支（Spring Boot 2.7）使用 sentinel-spring-webmvc-adapter：import 的包名改成
     * com.alibaba.csp.sentinel.adapter.spring.webmvc.callback，lambda 去掉 resourceName 参数，参见项目文档
     */
    @Bean
    public BlockExceptionHandler demoSentinelBlockExceptionHandler() {
        return (request, response, resourceName, ex) -> {
            HttpStatus status;
            CommonResult<?> result;
            if (ex instanceof DegradeException) {
                status = HttpStatus.SERVICE_UNAVAILABLE;
                result = CommonResult.error(status.value(), "服务暂时不可用，请稍后重试");
            } else {
                status = HttpStatus.TOO_MANY_REQUESTS;
                result = CommonResult.error(TOO_MANY_REQUESTS);
            }
            response.setStatus(status.value());
            ServletUtils.writeJSON(response, result);
        };
    }

}
