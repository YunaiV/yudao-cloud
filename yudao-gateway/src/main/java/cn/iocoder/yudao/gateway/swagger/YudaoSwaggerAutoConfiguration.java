package cn.iocoder.yudao.gateway.swagger;

import com.github.xiaoymin.knife4j.spring.gateway.configuration.Knife4jGatewayAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 网关 Swagger 接口文档的自动配置类
 *
 * 参考 {@link Knife4jGatewayAutoConfiguration} 实现，进行功能的增强，核心实现在 {@link SwaggerResourceHandlerFunction} 类中
 * 它通过解析 spring.cloud.gateway.routes 配置，获得 Swagger 资源分组。
 *
 * 另外，目前官方 Knif4j 网关的实现，不会通过注册中心加载对应的 URL 地址。等到他们完善了，就可以去掉自己的这个实现了。
 *
 * @see <a href="https://doc.xiaominfo.com/docs/middleware-sources/spring-cloud-gateway/spring-gateway-introduction">Knife4j + Spring Cloud Gateway 网关聚合</a>
 *
 * @author 芋道源码
 */
@Configuration
@ConditionalOnProperty(name = "knife4j.gateway.enable", havingValue = "true")
@Slf4j
public class YudaoSwaggerAutoConfiguration {

    /**
     * Swagger 资源分组 URL
     */
    public static final String GATEWAY_SWAGGER_GROUP_URL = "/swagger-resources";

    @Bean
    public RouterFunction<ServerResponse> swaggerResourceHandlerFunction(GatewayProperties gatewayProperties) {
        log.info("[swaggerResourceHandlerFunction][初始化完成]");
        SwaggerResourceHandlerFunction handlerFunction = new SwaggerResourceHandlerFunction(gatewayProperties);
        return RouterFunctions.route().GET(GATEWAY_SWAGGER_GROUP_URL, handlerFunction).build();
    }

}
