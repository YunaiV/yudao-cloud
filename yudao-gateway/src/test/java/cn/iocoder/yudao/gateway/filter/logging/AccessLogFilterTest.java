package cn.iocoder.yudao.gateway.filter.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link AccessLogFilter} 的单元测试
 *
 * @author 芋道源码
 */
public class AccessLogFilterTest {

    private static final String TOKEN = "secret_refresh_token";
    private static final String AUTHORIZATION = "Bearer secret_access_token";
    private static final String COOKIE = "JSESSIONID=secret_session";
    private static final String SET_COOKIE = "JSESSIONID=secret_new_session; Path=/";
    private static final String JDBC_PASSWORD = "secret_jdbc_password";

    private final AccessLogFilter filter = new AccessLogFilter();

    private Logger logger;
    private Level oldLevel;
    private ListAppender<ILoggingEvent> appender;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(filter, "codecConfigurer", ServerCodecConfigurer.create());
        // 收集网关日志
        logger = (Logger) LoggerFactory.getLogger(AccessLogFilter.class);
        oldLevel = logger.getLevel();
        logger.setLevel(Level.INFO);
        appender = new ListAppender<>();
        appender.start();
        logger.addAppender(appender);
    }

    @AfterEach
    public void tearDown() {
        logger.detachAppender(appender);
        logger.setLevel(oldLevel);
    }

    @Test
    public void testFilter_ureportWithoutBody() {
        // 准备参数
        MockServerHttpRequest request = MockServerHttpRequest
                .get("/ureport/preview?_u=db:demo.ureport.xml&token={token}&tenantId=1", TOKEN)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION)
                .header(HttpHeaders.COOKIE, COOKIE)
                .build();

        // 调用
        ServerWebExchange forwarded = doFilter(MockServerWebExchange.from(request), null);
        // 断言：实际的请求不变
        assertEquals(TOKEN, forwarded.getRequest().getQueryParams().getFirst("token"));
        assertEquals(AUTHORIZATION, forwarded.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
        assertEquals(COOKIE, forwarded.getRequest().getHeaders().getFirst(HttpHeaders.COOKIE));
        // 断言：日志脱敏，保留诊断信息
        String accessLog = getAccessLog();
        assertFalse(accessLog.contains(TOKEN));
        assertFalse(accessLog.contains(AUTHORIZATION));
        assertFalse(accessLog.contains(COOKIE));
        assertTrue(accessLog.contains("/ureport/preview"));
        assertTrue(accessLog.contains("db:demo.ureport.xml"));
        assertTrue(accessLog.contains("tenantId"));
    }

    @Test
    public void testFilter_ureportWithBody() {
        // 准备参数：保存报表，模板中包含数据源的连接密码
        String body = "file=db%3Ademo.ureport.xml&content=%3Cdatasource+password%3D%22" + JDBC_PASSWORD + "%22%2F%3E";
        MockServerHttpRequest request = MockServerHttpRequest
                .post("/ureport/designer/saveReportFile?token={token}", TOKEN)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(HttpHeaders.COOKIE, COOKIE)
                .body(body);
        // 准备参数：响应包含模板内容、Cookie
        String responseBody = "{\"content\":\"<datasource password=\\\"" + JDBC_PASSWORD + "\\\"/>\"}";

        // 调用
        AtomicReference<String> forwardedBody = new AtomicReference<>();
        ServerWebExchange forwarded = doFilter(MockServerWebExchange.from(request), exchange ->
                DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
                    forwardedBody.set(dataBuffer.toString(StandardCharsets.UTF_8));
                    DataBufferUtils.release(dataBuffer);
                    return writeJsonResponse(exchange, responseBody);
                }));
        // 断言：实际的请求不变
        assertEquals(body, forwardedBody.get());
        assertEquals(TOKEN, forwarded.getRequest().getQueryParams().getFirst("token"));
        assertEquals(COOKIE, forwarded.getRequest().getHeaders().getFirst(HttpHeaders.COOKIE));
        // 断言：日志脱敏，不记录请求和响应体
        String accessLog = getAccessLog();
        assertFalse(accessLog.contains(TOKEN));
        assertFalse(accessLog.contains(COOKIE));
        assertFalse(accessLog.contains("secret_new_session"));
        assertFalse(accessLog.contains(JDBC_PASSWORD));
        assertTrue(accessLog.contains("/ureport/designer/saveReportFile"));
    }

    @Test
    public void testFilter_ureportEncodedPath() {
        // 准备参数：编码后的路径
        MockServerHttpRequest request = MockServerHttpRequest.method(HttpMethod.GET,
                URI.create("/%75report/preview?token=" + TOKEN)).build();

        // 调用
        doFilter(MockServerWebExchange.from(request), null);
        // 断言
        assertFalse(getAccessLog().contains(TOKEN));
    }

    @Test
    public void testFilter_otherUrl() {
        // 准备参数：非 UReport2 报表的请求，日志保持原样
        MockServerHttpRequest request = MockServerHttpRequest
                .get("/admin-api/system/user/page?token={token}", TOKEN)
                .build();

        // 调用
        doFilter(MockServerWebExchange.from(request), null);
        // 断言
        assertTrue(getAccessLog().contains(TOKEN));
    }

    private ServerWebExchange doFilter(ServerWebExchange exchange, GatewayFilterChain handler) {
        AtomicReference<ServerWebExchange> forwarded = new AtomicReference<>();
        GatewayFilterChain chain = filteredExchange -> {
            forwarded.set(filteredExchange);
            return handler != null ? handler.filter(filteredExchange) : Mono.empty();
        };
        filter.filter(exchange, chain).block();
        assertNotNull(forwarded.get());
        return forwarded.get();
    }

    private static Mono<Void> writeJsonResponse(ServerWebExchange exchange, String content) {
        exchange.getAttributes().put(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR,
                MediaType.APPLICATION_JSON_VALUE);
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        exchange.getResponse().getHeaders().add(HttpHeaders.SET_COOKIE, SET_COOKIE);
        return exchange.getResponse().writeWith(Flux.just(exchange.getResponse().bufferFactory()
                .wrap(content.getBytes(StandardCharsets.UTF_8))));
    }

    private String getAccessLog() {
        return appender.list.stream().map(ILoggingEvent::getFormattedMessage)
                .filter(message -> message.contains("网关日志"))
                .findFirst().orElseThrow(() -> new AssertionError("未打印网关日志"));
    }

}
