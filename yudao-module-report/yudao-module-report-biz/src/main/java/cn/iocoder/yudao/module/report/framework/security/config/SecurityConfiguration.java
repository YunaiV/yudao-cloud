package cn.iocoder.yudao.module.report.framework.security.config;

import cn.iocoder.yudao.framework.security.config.AuthorizeRequestsCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Report 模块的 Security 配置
 */
@Configuration("reportSecurityConfiguration")
public class SecurityConfiguration {

    @Bean("reportAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                // Swagger 接口文档
                registry.antMatchers("/v3/api-docs/**").permitAll() // 元数据
                        .antMatchers("/swagger-ui.html").permitAll(); // Swagger UI
                // Spring Boot Actuator 的安全配置
                registry.antMatchers("/actuator").anonymous()
                        .antMatchers("/actuator/**").anonymous();
                // Druid 监控
                registry.antMatchers("/druid/**").anonymous();
                // 积木报表
                registry.antMatchers("/jmreport/**").permitAll();
            }

        };
    }

}
