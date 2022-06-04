package cn.iocoder.yudao.gateway.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * Web 工具类
 *
 * copy from yudao-spring-boot-starter-web 的 WebFrameworkUtils 类
 *
 * @author 芋道源码
 */
@Slf4j
public class WebFrameworkUtils {

    @SuppressWarnings("UastIncorrectHttpHeaderInspection")
    private static final String HEADER_TENANT_ID = "tenant-id";

    private WebFrameworkUtils() {}

    /**
     * 将 Gateway 请求中的 header，设置到 HttpHeaders 中
     *
     * @param exchange Gateway 请求
     * @param httpHeaders WebClient 的请求
     */
    public static void setTenantIdHeader(ServerWebExchange exchange, HttpHeaders httpHeaders) {
        String tenantId = exchange.getRequest().getHeaders().getFirst(HEADER_TENANT_ID);
        if (StrUtil.isNotEmpty(tenantId)) {
            return;
        }
        httpHeaders.set(HEADER_TENANT_ID, tenantId);
    }

    /**
     * 返回 JSON 字符串
     *
     * @param exchange 响应
     * @param object 对象，会序列化成 JSON 字符串
     */
    @SuppressWarnings("deprecation") // 必须使用 APPLICATION_JSON_UTF8_VALUE，否则会乱码
    public static Mono<Void> writeJSON(ServerWebExchange exchange, Object object) {
        // 设置 header
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 设置 body
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                return bufferFactory.wrap(JsonUtils.toJsonByte(object));
            } catch (Exception ex) {
                ServerHttpRequest request = exchange.getRequest();
                log.error("[writeJSON][uri({}/{}) 发生异常]", request.getURI(), request.getMethod(), ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }

}
