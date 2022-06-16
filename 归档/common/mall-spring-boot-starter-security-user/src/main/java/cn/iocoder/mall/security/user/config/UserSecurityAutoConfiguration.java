package cn.iocoder.mall.security.user.config;

import cn.iocoder.mall.security.user.core.interceptor.UserSecurityInterceptor;
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
@EnableConfigurationProperties(UserSecurityProperties.class)
public class UserSecurityAutoConfiguration implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    @ConditionalOnMissingBean
    public UserSecurityProperties userSecurityProperties() {
        return new UserSecurityProperties();
    }

    // ========== 拦截器相关 ==========

    @Bean
    public UserSecurityInterceptor userSecurityInterceptor() {
        return new UserSecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        UserSecurityProperties properties = this.userSecurityProperties();
        // UserSecurityInterceptor 拦截器
        registry.addInterceptor(this.userSecurityInterceptor())
                .excludePathPatterns(properties.getIgnorePaths())
                .excludePathPatterns(properties.getDefaultIgnorePaths());;
        logger.info("[addInterceptors][加载 UserSecurityInterceptor 拦截器完成]");
    }

}
