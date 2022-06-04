package cn.iocoder.yudao.gateway.util;

import cn.hutool.core.map.MapUtil;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

/**
 * 安全服务工具类
 *
 * copy from yudao-spring-boot-starter-security 的 SecurityFrameworkUtils 类
 *
 * @author 芋道源码
 */
public class SecurityFrameworkUtils {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String AUTHORIZATION_BEARER = "Bearer";

    private static final String LOGIN_USER_HEADER = "login-user";

    private SecurityFrameworkUtils() {}

    /**
     * 从请求中，获得认证 Token
     *
     * @param exchange 请求
     * @return 认证 Token
     */
    public static String obtainAuthorization(ServerWebExchange exchange) {
        String authorization = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION_HEADER);
        if (!StringUtils.hasText(authorization)) {
            return null;
        }
        int index = authorization.indexOf(AUTHORIZATION_BEARER + " ");
        if (index == -1) { // 未找到
            return null;
        }
        return authorization.substring(index + 7).trim();
    }

    /**
     * 将访问令牌封装成 LoginUser，并设置到 login-user 的请求头，使用 json 存储值
     *
     * @param builder 请求
     * @param token 访问令牌
     */
    public static void setLoginUserHeader(ServerHttpRequest.Builder builder, OAuth2AccessTokenCheckRespDTO token) {
        // 构建 LoginUser 对象。由于 Gateway 没有 loginUser 类，所以使用 Map
        Map<String, Object> loginUser = MapUtil.newHashMap(4);
        loginUser.put("id", token.getUserId());
        loginUser.put("userType", token.getUserType());
        loginUser.put("tenantId", token.getTenantId());
        loginUser.put("scopes", token.getScopes());
        // 设置到 Header 中
        builder.header(LOGIN_USER_HEADER, JsonUtils.toJsonString(loginUser));
    }

}
