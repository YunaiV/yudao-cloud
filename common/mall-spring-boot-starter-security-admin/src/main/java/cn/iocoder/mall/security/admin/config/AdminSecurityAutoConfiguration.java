package cn.iocoder.mall.security.admin.config;

import cn.iocoder.mall.security.admin.core.interceptor.AdminDemoInterceptor;
import cn.iocoder.mall.security.admin.core.interceptor.AdminSecurityInterceptor;
import cn.iocoder.mall.web.config.CommonWebAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AutoConfigureAfter(CommonWebAutoConfiguration.class) // 在 CommonWebAutoConfiguration 之后自动配置，保证过滤器的顺序
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties(AdminSecurityProperties.class)
public class AdminSecurityAutoConfiguration implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    @ConditionalOnMissingBean
    public AdminSecurityProperties adminSecurityProperties() {
        return new AdminSecurityProperties();
    }

    // ========== 拦截器相关 ==========

    @Bean
    public AdminSecurityInterceptor adminSecurityInterceptor() {
        return new AdminSecurityInterceptor();
    }

    @Bean
    public AdminDemoInterceptor adminDemoInterceptor() {
        return new AdminDemoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AdminSecurityProperties properties = this.adminSecurityProperties();
        // AdminSecurityInterceptor 拦截器
        registry.addInterceptor(this.adminSecurityInterceptor())
                .excludePathPatterns(properties.getIgnorePaths())
                .excludePathPatterns(properties.getDefaultIgnorePaths());
        logger.info("[addInterceptors][加载 AdminSecurityInterceptor 拦截器完成]");
        // AdminDemoInterceptor 拦截器
        if (Boolean.TRUE.equals(properties.getDemo())) {
            registry.addInterceptor(this.adminDemoInterceptor())
                    .excludePathPatterns(properties.getIgnorePaths())
                    .excludePathPatterns(properties.getDefaultIgnorePaths());
            logger.info("[addInterceptors][加载 AdminDemoInterceptor 拦截器完成]");
        }
    }

}
