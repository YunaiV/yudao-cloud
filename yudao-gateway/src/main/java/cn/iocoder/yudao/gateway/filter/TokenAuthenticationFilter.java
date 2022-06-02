package cn.iocoder.yudao.gateway.filter;

import cn.iocoder.yudao.module.system.api.auth.OAuth2TokenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * Token 过滤器，验证 token 的有效性
 * 1. 验证通过时，将 userId、userType、tenantId 通过 Header 转发给服务
 * 2. 验证不通过，还是会转发给服务。因为，接口是否需要登录的校验，还是交给服务自身处理
 *
 * @author 芋道源码
 */
@Component // TODO 芋艿：要改成 configuration
public class TokenAuthenticationFilter implements GlobalFilter, Ordered {

    @Resource
    private OAuth2TokenApi oauth2TokenApi;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange = exchange.mutate().request(r -> r.headers(new Consumer<HttpHeaders>() {
            @Override
            public void accept(HttpHeaders headers) {
                headers.set("user-id", "1");
            }
        })).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100; // 和 Spring Security Filter 的顺序对齐
    }

}
