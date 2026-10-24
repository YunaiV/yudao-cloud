package cn.iocoder.yudao.gateway.sentineldemo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.nio.charset.StandardCharsets;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.TOO_MANY_REQUESTS;

/**
 * 网关 Sentinel 的配置类
 *
 * 网关的请求被 Sentinel 阻断时，返回对应的 HTTP 状态码 + CommonResult 格式的 JSON，前端可以读取其中的 code、msg：
 * 1. 熔断：HTTP 503，code = 503
 * 2. 限流等其它阻断：HTTP 429，code = 429。网关的流控规则在 Sentinel 内部转换成热点参数规则，被限流时抛出的是 ParamFlowException
 *
 * spring-cloud-alibaba-sentinel-gateway 会将这里的 BlockRequestHandler 注册到 GatewayCallbackManager，
 * 由优先级最高的 SentinelGatewayBlockExceptionHandler 调用，不会进入网关的 GlobalExceptionHandler。
 * 注意，不要再配置 spring.cloud.sentinel.scg.fallback.*，否则它会覆盖这里的处理器
 *
 * @author 芋道源码
 */
@Configuration(value = "demoSentinelGatewayConfiguration", proxyBeanMethods = false)
public class DemoSentinelGatewayConfiguration {

    /**
     * 显式声明 charset，避免部分客户端按 ISO-8859-1 解码中文的 msg
     */
    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);

    @Bean
    public BlockRequestHandler demoSentinelBlockRequestHandler() {
        return (exchange, ex) -> {
            HttpStatus status;
            CommonResult<?> result;
            if (ex instanceof DegradeException) {
                status = HttpStatus.SERVICE_UNAVAILABLE;
                result = CommonResult.error(status.value(), "服务暂时不可用，请稍后重试");
            } else {
                status = HttpStatus.TOO_MANY_REQUESTS;
                result = CommonResult.error(TOO_MANY_REQUESTS);
            }
            return ServerResponse.status(status).contentType(APPLICATION_JSON_UTF8)
                    .bodyValue(JsonUtils.toJsonString(result));
        };
    }

}
