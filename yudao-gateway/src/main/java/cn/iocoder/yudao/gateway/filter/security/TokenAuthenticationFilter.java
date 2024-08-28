package cn.iocoder.yudao.gateway.filter.security;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.core.KeyValue;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Function;

import static cn.iocoder.yudao.framework.common.util.cache.CacheUtils.buildAsyncReloadingCache;

/**
 * Token 过滤器，验证 token 的有效性
 * 1. 验证通过时，将 userId、userType、tenantId 通过 Header 转发给服务
 * 2. 验证不通过，还是会转发给服务。因为，接口是否需要登录的校验，还是交给服务自身处理
 *
 * @author 芋道源码
 */
@Component
public class TokenAuthenticationFilter implements GlobalFilter, Ordered {

    /**
     * CommonResult<OAuth2AccessTokenCheckRespDTO> 对应的 TypeReference 结果，用于解析 checkToken 的结果
     */
    private static final TypeReference<CommonResult<OAuth2AccessTokenCheckRespDTO>> CHECK_RESULT_TYPE_REFERENCE
            = new TypeReference<CommonResult<OAuth2AccessTokenCheckRespDTO>>() {};

    /**
     * 空的 LoginUser 的结果
     *
     * 用于解决如下问题：
     * 1. {@link #getLoginUser(ServerWebExchange, String)} 返回 Mono.empty() 时，会导致后续的 flatMap 无法进行处理的问题。
     * 2. {@link #buildUser(String)} 时，如果 Token 已经过期，返回 LOGIN_USER_EMPTY 对象，避免缓存无法刷新
     */
    private static final LoginUser LOGIN_USER_EMPTY = new LoginUser();

    private final WebClient webClient;

    /**
     * 登录用户的本地缓存
     *
     * key1：多租户的编号
     * key2：访问令牌
     */
    private final LoadingCache<KeyValue<Long, String>, LoginUser> loginUserCache = buildAsyncReloadingCache(Duration.ofMinutes(1),
            new CacheLoader<KeyValue<Long, String>, LoginUser>() {

                @Override
                public LoginUser load(KeyValue<Long, String> token) {
                    String body = checkAccessToken(token.getKey(), token.getValue()).block();
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
        // 重要说明：defaultIfEmpty 作用，保证 Mono.empty() 情况，可以继续执行 `flatMap 的 chain.filter(exchange)` 逻辑，避免返回给前端空的 Response！！
        return getLoginUser(exchange, token).defaultIfEmpty(LOGIN_USER_EMPTY).flatMap(user -> {
            // 1. 无用户，直接 filter 继续请求
            if (user == LOGIN_USER_EMPTY || // 下面 expiresTime 的判断，为了解决 token 实际已经过期的情况
                    user.getExpiresTime() == null || LocalDateTimeUtils.beforeNow(user.getExpiresTime())) {
                return chain.filter(exchange);
            }

            // 2.1 有用户，则设置登录用户
            SecurityFrameworkUtils.setLoginUser(exchange, user);
            // 2.2 将 user 并设置到 login-user 的请求头，使用 json 存储值
            ServerWebExchange newExchange = exchange.mutate()
                    .request(builder -> SecurityFrameworkUtils.setLoginUserHeader(builder, user)).build();
            return chain.filter(newExchange);
        });
    }

    private Mono<LoginUser> getLoginUser(ServerWebExchange exchange, String token) {
        // 从缓存中，获取 LoginUser
        Long tenantId = WebFrameworkUtils.getTenantId(exchange);
        KeyValue<Long, String> cacheKey = new KeyValue<Long, String>().setKey(tenantId).setValue(token);
        LoginUser localUser = loginUserCache.getIfPresent(cacheKey);
        if (localUser != null) {
            return Mono.just(localUser);
        }

        // 缓存不存在，则请求远程服务
        return checkAccessToken(tenantId, token).flatMap((Function<String, Mono<LoginUser>>) body -> {
            LoginUser remoteUser = buildUser(body);
            if (remoteUser != null) {
                // 非空，则进行缓存
                loginUserCache.put(cacheKey, remoteUser);
                return Mono.just(remoteUser);
            }
            return Mono.empty();
        });
    }

    private Mono<String> checkAccessToken(Long tenantId, String token) {
        return webClient.get()
                .uri(OAuth2TokenApi.URL_CHECK, uriBuilder -> uriBuilder.queryParam("accessToken", token).build())
                .headers(httpHeaders -> WebFrameworkUtils.setTenantIdHeader(tenantId, httpHeaders)) // 设置租户的 Header
                .retrieve().bodyToMono(String.class);
    }

    private LoginUser buildUser(String body) {
        // 处理结果，结果不正确
        CommonResult<OAuth2AccessTokenCheckRespDTO> result = JsonUtils.parseObject(body, CHECK_RESULT_TYPE_REFERENCE);
        if (result == null) {
            return null;
        }
        if (result.isError()) {
            // 特殊情况：令牌已经过期（code = 401），需要返回 LOGIN_USER_EMPTY，避免 Token 一直因为缓存，被误判为有效
            if (Objects.equals(result.getCode(), HttpStatus.UNAUTHORIZED.value())) {
                return LOGIN_USER_EMPTY;
            }
            return null;
        }

        // 创建登录用户
        OAuth2AccessTokenCheckRespDTO tokenInfo = result.getData();
        return new LoginUser().setId(tokenInfo.getUserId()).setUserType(tokenInfo.getUserType())
                .setInfo(tokenInfo.getUserInfo()) // 额外的用户信息
                .setTenantId(tokenInfo.getTenantId()).setScopes(tokenInfo.getScopes())
                .setExpiresTime(tokenInfo.getExpiresTime());
    }

    @Override
    public int getOrder() {
        return -100; // 和 Spring Security Filter 的顺序对齐
    }

}
