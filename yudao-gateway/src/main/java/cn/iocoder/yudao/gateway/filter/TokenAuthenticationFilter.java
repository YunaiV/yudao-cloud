package cn.iocoder.yudao.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.gateway.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.api.oauth2.OAuth2TokenApi;
import cn.iocoder.yudao.module.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import cn.iocoder.yudao.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.net.HttpHeaders;
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
@Component // TODO 芋艿：要改成 configuration
public class TokenAuthenticationFilter implements GlobalFilter, Ordered {

//    @Resource
//    private OAuth2TokenApi oauth2TokenApi;

    @Resource
    private WebClient webClient;

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = SecurityFrameworkUtils.obtainAuthorization(exchange);
        // 情况一，如果没有 Token 令牌，则直接继续 filter
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }

//        exchange = exchange.mutate().request(r -> r.headers(new Consumer<HttpHeaders>() {
//            @Override
//            public void accept(HttpHeaders headers) {
//                headers.set("user-id", "1");
//            }
//        })).build();


//        return Mono.fromCallable(new Callable<CommonResult<OAuth2AccessTokenCheckRespDTO>>() {
//            @Override
//            public CommonResult<OAuth2AccessTokenCheckRespDTO> call() throws Exception {
////                return oauth2TokenApi.checkAccessToken("1234");
//                return CommonResult.success(new OAuth2AccessTokenCheckRespDTO().setUserId(1L));
//            }
//        }).subscribeOn(Schedulers.boundedElastic()).flatMap(new Function<CommonResult<OAuth2AccessTokenCheckRespDTO>, Mono<Void>>() {
//            @Override
//            public Mono<Void> apply(CommonResult<OAuth2AccessTokenCheckRespDTO> oAuth2AccessTokenCheckRespDTOCommonResult) {
//                return chain.filter(exchange);
//            }
//        });

        // 情况二，如果有 Token 令牌，则解析对应 userId、userType、tenantId 等字段，并通过 通过 Header 转发给服务
        // TODO 芋艿：tenant-id
        String tenantId = exchange.getRequest().getHeaders().getFirst("tenant-id");
        return webClient.get()
                .uri(OAuth2TokenApi.URL_CHECK, uriBuilder -> uriBuilder.queryParam("accessToken", token).build())
                .header("tenant-id", tenantId)
                .retrieve().bodyToMono(String.class) // 发起请求，设置 body 为 String 结果
                // 处理请求的结果
                .flatMap((Function<String, Mono<Void>>) body -> chain.filter(buildNewServerWebExchange(exchange, body)));
    }

    private ServerWebExchange buildNewServerWebExchange(ServerWebExchange exchange, String body) {
        // 校验 Token 令牌失败，则直接返回
        CommonResult<?> result = JsonUtils.parseObject(body, CommonResult.class);
        if (result == null || result.isError()) {
            return exchange;
        }
        // 创建新的 exchange 对象
        return exchange.mutate().request(builder -> builder.header("login-user", result.getData().toString())).build();
    }

    @Override
    public int getOrder() {
        return -100; // 和 Spring Security Filter 的顺序对齐
    }

}
