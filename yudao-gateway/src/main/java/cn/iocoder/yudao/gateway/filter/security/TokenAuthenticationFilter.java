package cn.iocoder.yudao.gateway.filter.security;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.gateway.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.gateway.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.system.api.oauth2.OAuth2TokenApi;
import cn.iocoder.yudao.module.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Token 过滤器，验证 token 的有效性
 * 1. 验证通过时，将 userId、userType、tenantId 通过 Header 转发给服务
 * 2. 验证不通过，还是会转发给服务。因为，接口是否需要登录的校验，还是交给服务自身处理
 *
 * @author 芋道源码
 */
@Component
public class TokenAuthenticationFilter implements GlobalFilter, Ordered {

    private static final TypeReference<CommonResult<OAuth2AccessTokenCheckRespDTO>> CHECK_RESULT_TYPE_REFERENCE
            = new TypeReference<CommonResult<OAuth2AccessTokenCheckRespDTO>>() {};

    private final WebClient webClient;

    public TokenAuthenticationFilter(ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        // Q：为什么不使用 OAuth2TokenApi 进行调用？
        // A1：Spring Cloud OpenFeign 官方未内置 Reactive 的支持 https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/#reactive-support
        // A2：校验 Token 的 API 需要使用到 header[tenant-id] 传递租户编号，暂时不想编写 RequestInterceptor 实现
        // 因此，这里采用 WebClient，通过 lbFunction 实现负载均衡
        this.webClient = WebClient.builder().filter(lbFunction).build();
    }

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = SecurityFrameworkUtils.obtainAuthorization(exchange);
        // 情况一，如果没有 Token 令牌，则直接继续 filter
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }

        // 情况二，如果有 Token 令牌，则解析对应 userId、userType、tenantId 等字段，并通过 通过 Header 转发给服务
        return webClient.get()
                .uri(OAuth2TokenApi.URL_CHECK, uriBuilder -> uriBuilder.queryParam("accessToken", token).build())
                .headers(httpHeaders -> WebFrameworkUtils.setTenantIdHeader(exchange, httpHeaders)) // 设置租户的 Header
                .retrieve().bodyToMono(String.class) // 发起请求，设置 body 为 String 结果
                .flatMap((Function<String, Mono<Void>>) body -> chain.filter(buildNewServerWebExchange(exchange, body))); // 处理请求的结果
    }

    private ServerWebExchange buildNewServerWebExchange(ServerWebExchange exchange, String body) {
        // 校验 Token 令牌失败，则直接返回
        CommonResult<OAuth2AccessTokenCheckRespDTO> result = JsonUtils.parseObject(body, CHECK_RESULT_TYPE_REFERENCE);
        if (result == null || result.isError()) {
            return exchange;
        }

        // 将访问令牌封装成 LoginUser，并设置到 login-user 的请求头，使用 json 存储值
        return exchange.mutate().request(builder -> SecurityFrameworkUtils.setLoginUserHeader(builder, result.getData())).build();
    }

    @Override
    public int getOrder() {
        return -100; // 和 Spring Security Filter 的顺序对齐
    }

}
