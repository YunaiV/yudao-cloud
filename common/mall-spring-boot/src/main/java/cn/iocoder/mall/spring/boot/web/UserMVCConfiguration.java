package cn.iocoder.mall.spring.boot.web;

import cn.iocoder.mall.spring.boot.constant.RootRequestPath;
import cn.iocoder.mall.user.sdk.interceptor.UserAccessLogInterceptor;
import cn.iocoder.mall.user.sdk.interceptor.UserSecurityInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // TODO 芋艿，未来可能考虑 REACTIVE
@ConditionalOnClass({DispatcherServlet.class, WebMvcConfigurer.class, // 有 Spring MVC 容器
        UserSecurityInterceptor.class, UserAccessLogInterceptor.class}) // 有引入 system-sdk
public class UserMVCConfiguration implements WebMvcConfigurer {

    @Bean
    @ConditionalOnMissingBean(UserAccessLogInterceptor.class)
    public UserAccessLogInterceptor userAccessLogInterceptor() {
        return new UserAccessLogInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(UserSecurityInterceptor.class)
    public UserSecurityInterceptor userSecurityInterceptor() {
        return new UserSecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAccessLogInterceptor()).addPathPatterns(RootRequestPath.USER + "/**");
        registry.addInterceptor(userSecurityInterceptor()).addPathPatterns(RootRequestPath.USER + "/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(RootRequestPath.USER + "/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true).maxAge(1800);
    }

}
