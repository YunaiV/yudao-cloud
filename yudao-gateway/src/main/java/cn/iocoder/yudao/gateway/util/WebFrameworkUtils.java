package cn.iocoder.yudao.gateway.util;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Web 工具类
 *
 * copy from yudao-spring-boot-starter-web 的 WebFrameworkUtils 类
 *
 * @author 芋道源码
 */
@Slf4j
public class WebFrameworkUtils {

    private static final String HEADER_TENANT_ID = "tenant-id";

    private WebFrameworkUtils() {}

    /**
     * 将 Gateway 请求中的 header，设置到 HttpHeaders 中
     *
     * @param tenantId 租户编号
     * @param httpHeaders WebClient 的请求
     */
    public static void setTenantIdHeader(Long tenantId, HttpHeaders httpHeaders) {
        if (tenantId == null) {
            return;
        }
        httpHeaders.set(HEADER_TENANT_ID, String.valueOf(tenantId));
    }

    public static Long getTenantId(ServerWebExchange exchange) {
        String tenantId = exchange.getRequest().getHeaders().getFirst(HEADER_TENANT_ID);
        return NumberUtil.isNumber(tenantId) ? Long.valueOf(tenantId) : null;
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

    /**
     * 获得客户端 IP
     *
     * 参考 {@link ServletUtil} 的 getClientIP 方法
     *
     * @param exchange 请求
     * @param otherHeaderNames 其它 header 名字的数组
     * @return 客户端 IP
     */
    public static String getClientIP(ServerWebExchange exchange, String... otherHeaderNames) {
        String[] headers = { "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };
        if (ArrayUtil.isNotEmpty(otherHeaderNames)) {
            headers = ArrayUtil.addAll(headers, otherHeaderNames);
        }
        // 方式一，通过 header 获取
        String ip;
        for (String header : headers) {
            ip = exchange.getRequest().getHeaders().getFirst(header);
            if (!NetUtil.isUnknown(ip)) {
                return NetUtil.getMultistageReverseProxyIp(ip);
            }
        }

        // 方式二，通过 remoteAddress 获取
        if (exchange.getRequest().getRemoteAddress() == null) {
            return null;
        }
        ip = exchange.getRequest().getRemoteAddress().getHostString();
        return NetUtil.getMultistageReverseProxyIp(ip);
    }

    /**
     * 获得请求匹配的 Route 路由
     *
     * @param exchange 请求
     * @return 路由
     */
    public static Route getGatewayRoute(ServerWebExchange exchange) {
        return exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    }

}
