package cn.iocoder.yudao.gateway.filter.security;

import cn.iocoder.yudao.framework.common.core.KeyValue;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * {@link TokenAuthenticationFilter} 的单元测试
 *
 * @author 芋道源码
 */
public class TokenAuthenticationFilterTest {

    private static final String LOGIN_USER_HEADER = "login-user";
    private static final String DATA_PERMISSION_ENABLE_HEADER = "data-permission-enable";

    private final TokenAuthenticationFilter filter = new TokenAuthenticationFilter(
            mock(ReactorLoadBalancerExchangeFilterFunction.class));

    @Test
    public void testFilter_noToken() {
        // 准备参数：无 Bearer Token，伪造内部 Header
        MockServerHttpRequest request = MockServerHttpRequest.get("/admin-api/crm/customer/page")
                .header("tenant-id", "1")
                .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdDp0ZXN0")
                .header("X-Custom", "custom")
                .header(LOGIN_USER_HEADER, "forged")
                .header(DATA_PERMISSION_ENABLE_HEADER, "false")
                .build();

        // 调用
        HttpHeaders headers = doFilterAndGetHeaders(MockServerWebExchange.from(request));
        // 断言：移除内部 Header
        assertFalse(headers.containsKey(LOGIN_USER_HEADER));
        assertFalse(headers.containsKey(DATA_PERMISSION_ENABLE_HEADER));
        // 断言：保留无关 Header
        assertEquals("1", headers.getFirst("tenant-id"));
        assertEquals("Basic dGVzdDp0ZXN0", headers.getFirst(HttpHeaders.AUTHORIZATION));
        assertEquals("custom", headers.getFirst("X-Custom"));
    }

    @Test
    public void testFilter_noTokenWithDuplicateAndCaseVariantHeaders() {
        // 准备参数：重复、大小写变体的 Header
        MockServerHttpRequest request = MockServerHttpRequest.get("/app-api/trade/order/page")
                .header("tenant-id", "1")
                .header(DATA_PERMISSION_ENABLE_HEADER, "false")
                .header("Data-Permission-Enable", "false")
                .header("DATA-PERMISSION-ENABLE", "FALSE")
                .header("Login-User", "forged")
                .header("LOGIN-USER", "forged")
                .build();

        // 调用
        HttpHeaders headers = doFilterAndGetHeaders(MockServerWebExchange.from(request));
        // 断言
        assertNull(headers.get(DATA_PERMISSION_ENABLE_HEADER));
        assertFalse(headers.containsKey("Data-Permission-Enable"));
        assertFalse(headers.containsKey("DATA-PERMISSION-ENABLE"));
        assertNull(headers.get(LOGIN_USER_HEADER));
        assertFalse(headers.containsKey("Login-User"));
        assertEquals("1", headers.getFirst("tenant-id"));
    }

    @Test
    public void testFilter_expiredToken() {
        // 准备参数：Token 已过期
        String token = "expired_token";
        putLoginUserCache(1L, token, new LoginUser().setId(100L).setUserType(2)
                .setExpiresTime(LocalDateTime.now().minusMinutes(1)));
        MockServerHttpRequest request = MockServerHttpRequest.get("/admin-api/system/user/page")
                .header("tenant-id", "1")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .header(LOGIN_USER_HEADER, "forged")
                .header(DATA_PERMISSION_ENABLE_HEADER, "false")
                .build();

        // 调用
        HttpHeaders headers = doFilterAndGetHeaders(MockServerWebExchange.from(request));
        // 断言
        assertFalse(headers.containsKey(LOGIN_USER_HEADER));
        assertFalse(headers.containsKey(DATA_PERMISSION_ENABLE_HEADER));
        assertEquals("1", headers.getFirst("tenant-id"));
        assertEquals("Bearer " + token, headers.getFirst(HttpHeaders.AUTHORIZATION));
    }

    @Test
    public void testFilter_validToken() {
        // 准备参数：Token 有效
        String token = "valid_token";
        LoginUser user = new LoginUser().setId(100L).setUserType(2).setTenantId(1L)
                .setExpiresTime(LocalDateTime.now().plusMinutes(30));
        putLoginUserCache(1L, token, user);
        MockServerHttpRequest request = MockServerHttpRequest.get("/admin-api/system/user/page")
                .header("tenant-id", "1")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .header(LOGIN_USER_HEADER, "forged")
                .header(DATA_PERMISSION_ENABLE_HEADER, "false")
                .build();

        // 调用
        HttpHeaders headers = doFilterAndGetHeaders(MockServerWebExchange.from(request));
        // 断言：login-user 被网关重新设置，data-permission-enable 被移除
        assertEquals(1, headers.get(LOGIN_USER_HEADER).size());
        assertEquals(cn.iocoder.yudao.framework.common.util.http.HttpUtils.encodeUtf8(JsonUtils.toJsonString(user)),
                headers.getFirst(LOGIN_USER_HEADER));
        assertFalse(headers.containsKey(DATA_PERMISSION_ENABLE_HEADER));
        assertEquals("1", headers.getFirst("tenant-id"));
    }

    private HttpHeaders doFilterAndGetHeaders(ServerWebExchange exchange) {
        AtomicReference<ServerWebExchange> exchangeRef = new AtomicReference<>();
        GatewayFilterChain chain = filteredExchange -> {
            exchangeRef.set(filteredExchange);
            return Mono.empty();
        };
        filter.filter(exchange, chain).block();
        assertNotNull(exchangeRef.get());
        return exchangeRef.get().getRequest().getHeaders();
    }

    @SuppressWarnings("unchecked")
    private void putLoginUserCache(Long tenantId, String token, LoginUser user) {
        LoadingCache<KeyValue<Long, String>, LoginUser> loginUserCache =
                (LoadingCache<KeyValue<Long, String>, LoginUser>) ReflectionTestUtils.getField(filter, "loginUserCache");
        assertNotNull(loginUserCache);
        loginUserCache.put(new KeyValue<>(tenantId, token), user);
    }

}
