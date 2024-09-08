package cn.iocoder.yudao.module.kafka.framework.security;

import cn.iocoder.yudao.framework.security.config.AuthorizeRequestsCustomizer;
import cn.iocoder.yudao.module.infra.enums.ApiConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author zyh
 * @ClassName: SecurityConfiguration
 * @Package cn.iocoder.yudao.module.kafka.framework.security
 * @Description TODO
 * @date 2024/9/4 15:45
 */
@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

    @Bean
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry) {
                authorizationManagerRequestMatcherRegistry.requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/swagger-ui").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll();
                // Druid 监控
                authorizationManagerRequestMatcherRegistry.requestMatchers("/druid/**").permitAll();
                // Spring Boot Actuator 的安全配置
                authorizationManagerRequestMatcherRegistry.requestMatchers("/actuator").permitAll()
                        .requestMatchers("/actuator/**").permitAll();
                // RPC 服务的安全配置
                authorizationManagerRequestMatcherRegistry.requestMatchers(ApiConstants.PREFIX + "/**").permitAll();
            }

        };
    }
}
