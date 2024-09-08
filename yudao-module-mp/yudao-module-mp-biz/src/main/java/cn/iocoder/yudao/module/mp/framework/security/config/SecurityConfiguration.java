package cn.iocoder.yudao.module.mp.framework.security.config;

import cn.iocoder.yudao.framework.security.config.AuthorizeRequestsCustomizer;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * System 模块的 Security 配置
 */
@Configuration(proxyBeanMethods = false, value = "systemSecurityConfiguration")
public class SecurityConfiguration {

    @Bean("systemAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                // TODO 芋艿：这个每个项目都需要重复配置，得捉摸有没通用的方案
                // Swagger 接口文档
                registry.antMatchers("/v3/api-docs/**").permitAll()
                        .antMatchers("/webjars/**").permitAll()
                        .antMatchers("/swagger-ui").permitAll()
                        .antMatchers("/swagger-ui/**").permitAll();
                // Druid 监控
                registry.antMatchers("/druid/**").anonymous();
                // Spring Boot Actuator 的安全配置
                registry.antMatchers("/actuator").anonymous()
                        .antMatchers("/actuator/**").anonymous();
                // RPC 服务的安全配置
                registry.antMatchers(ApiConstants.PREFIX + "/**").permitAll();
            }

        };
    }

}
