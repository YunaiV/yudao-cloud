package cn.iocoder.yudao.gateway.filter.cors;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 解决 Spring Cloud Gateway 2.x 跨域时，出现重复 Origin 的 BUG
 *
 * 参考文档：<a href="https://blog.csdn.net/zimou5581/article/details/90043178" />
 *
 * @author 芋道源码
 */
@Component
public class CorsResponseHeaderFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        // 指定此过滤器位于 NettyWriteResponseFilter 之后
        // 即待处理完响应体后接着处理响应头
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER + 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.defer(() -> {
            // https://gitee.com/zhijiantianya/yudao-cloud/pulls/177/
            List<String> keysToModify = exchange.getResponse().getHeaders().entrySet().stream()
                    .filter(kv -> (kv.getValue() != null && kv.getValue().size() > 1))
                    .filter(kv -> (kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                            || kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS)))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            keysToModify.forEach(key->{
                List<String> values = exchange.getResponse().getHeaders().get(key);
                if (values != null && !values.isEmpty()) {
                    exchange.getResponse().getHeaders().put(key, Collections.singletonList(values.get(0)));
                }
            });
            return chain.filter(exchange);
        }));
    }

}
