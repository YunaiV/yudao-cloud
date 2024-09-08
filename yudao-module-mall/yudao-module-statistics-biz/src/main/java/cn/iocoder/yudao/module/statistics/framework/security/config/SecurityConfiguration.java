package cn.iocoder.yudao.module.statistics.framework.security.config;

import cn.iocoder.yudao.framework.security.config.AuthorizeRequestsCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Statistics 模块的 Security 配置
 */
@Configuration("statisticsSecurityConfiguration")
public class SecurityConfiguration {

    @Bean("statisticsAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                // Swagger 接口文档
                registry.antMatchers("/v3/api-docs/**").permitAll()
                        .antMatchers("/webjars/**").permitAll()
                        .antMatchers("/swagger-ui").permitAll()
                        .antMatchers("/swagger-ui/**").permitAll();
                // Spring Boot Actuator 的安全配置
                registry.antMatchers("/actuator").anonymous()
                        .antMatchers("/actuator/**").anonymous();
                // Druid 监控
                registry.antMatchers("/druid/**").anonymous();
            }

        };
    }

}
