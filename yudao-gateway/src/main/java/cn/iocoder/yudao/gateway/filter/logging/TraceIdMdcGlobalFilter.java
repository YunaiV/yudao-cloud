package cn.iocoder.yudao.gateway.filter.logging;

import cn.iocoder.yudao.framework.tracer.core.util.TracerFrameworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 从 Reactor Context 中获取 traceId，填充到 MDC 中
 */
@Component
@Slf4j
public class TraceIdMdcGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 延迟从 Reactor Context 拿 traceId，保证 SkyWalking 已经初始化
        return Mono.deferContextual(ctxView -> {
            TracerFrameworkUtils.putTidIntoMdc(exchange);
            // chain.filter 返回的是 Mono<Void>，doFinally 确保每次请求结束清理 MDC
            return chain.filter(exchange)
                    .doFinally(signalType -> TracerFrameworkUtils.removeTidFromMdc());
        });
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}

