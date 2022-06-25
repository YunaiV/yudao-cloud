package cn.iocoder.yudao.gateway.filter.security;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.core.KeyValue;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.cache.CacheUtils;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.gateway.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.gateway.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.system.api.oauth2.OAuth2TokenApi;
import cn.iocoder.yudao.module.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
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

    private final LoadingCache<KeyValue<Long, String>, LoginUser> loginUserCache = CacheUtils.buildAsyncReloadingCache(Duration.ofMinutes(1),
            new CacheLoader<KeyValue<Long, String>, LoginUser>() {

                @Override
                public LoginUser load(KeyValue<Long, String> keyValue) {
                    String body = checkAccessToken(keyValue.getKey(), keyValue.getValue()).block();
                    return buildUser(body);
                }

            });

    public TokenAuthenticationFilter(ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        // Q：为什么不使用 OAuth2TokenApi 进行调用？
        // A1：Spring Cloud OpenFeign 官方未内置 Reactive 的支持 https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/#reactive-support
        // A2：校验 Token 的 API 需要使用到 header[tenant-id] 传递租户编号，暂时不想编写 RequestInterceptor 实现
        // 因此，这里采用 WebClient，通过 lbFunction 实现负载均衡
        this.webClient = WebClient.builder().filter(lbFunction).build();
    }

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, GatewayFilterChain chain) {
        // 移除 login-user 的请求头，避免伪造模拟
        SecurityFrameworkUtils.removeLoginUser(exchange);

        // 情况一，如果没有 Token 令牌，则直接继续 filter
        String token = SecurityFrameworkUtils.obtainAuthorization(exchange);
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }

        // 情况二，如果有 Token 令牌，则解析对应 userId、userType、tenantId 等字段，并通过 通过 Header 转发给服务
        Long tenantId = WebFrameworkUtils.getTenantId(exchange);
        KeyValue<Long, String> cacheKey = new KeyValue<Long, String>().setKey(tenantId).setValue(token);
        LoginUser user = loginUserCache.getUnchecked(cacheKey);
        if (user != null) {
            SecurityFrameworkUtils.setLoginUser(exchange, user);
            return chain.filter(exchange.mutate().request(builder -> SecurityFrameworkUtils.setLoginUserHeader(builder, user)).build());
        }
        return checkAccessToken(cacheKey.getKey(), token)
                .flatMap((Function<String, Mono<Void>>) body -> chain.filter(buildNewServerWebExchange(exchange, cacheKey, body))); // 处理请求的结果
    }

    private Mono<String> checkAccessToken(Long tenantId, String token) {
        return webClient.get()
                .uri(OAuth2TokenApi.URL_CHECK, uriBuilder -> uriBuilder.queryParam("accessToken", token).build())
                .headers(httpHeaders -> WebFrameworkUtils.setTenantIdHeader(tenantId, httpHeaders)) // 设置租户的 Header
                .retrieve().bodyToMono(String.class);
    }

    private ServerWebExchange buildNewServerWebExchange(ServerWebExchange exchange, KeyValue<Long, String> cacheKey, String body) {
        // 1.1 解析 User
        LoginUser user = buildUser(body);
        // 1.2 校验 Token 令牌失败，则直接返回
        if (user == null) {
            return exchange;
        }

        // 2. 设置到缓存
        loginUserCache.put(cacheKey, user);

        // 3.1 设置登录用户
        SecurityFrameworkUtils.setLoginUser(exchange, user);
        // 3.2 将 user 并设置到 login-user 的请求头，使用 json 存储值
        return exchange.mutate().request(builder -> SecurityFrameworkUtils.setLoginUserHeader(builder, user)).build();
    }

    private LoginUser buildUser(String body) {
        CommonResult<OAuth2AccessTokenCheckRespDTO> result = JsonUtils.parseObject(body, CHECK_RESULT_TYPE_REFERENCE);
        if (result == null || result.isError()) {
            return null;
        }
        // 创建登录用户
        OAuth2AccessTokenCheckRespDTO tokenInfo = result.getData();
        return new LoginUser().setId(tokenInfo.getUserId()).setUserType(tokenInfo.getUserType())
                .setTenantId(tokenInfo.getTenantId()).setScopes(tokenInfo.getScopes());
    }

    @Override
    public int getOrder() {
        return -100; // 和 Spring Security Filter 的顺序对齐
    }

}
