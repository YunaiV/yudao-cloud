package cn.iocoder.yudao.gateway.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;
import java.util.Map;

/**
 * Web 工具类
 *
 * copy from yudao-spring-boot-starter-web 的 WebFrameworkUtils 类
 *
 * @author 芋道源码
 */
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

}
