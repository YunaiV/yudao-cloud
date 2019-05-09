package cn.iocoder.mall.spring.boot.web;

import cn.iocoder.mall.admin.sdk.interceptor.AdminAccessLogInterceptor;
import cn.iocoder.mall.admin.sdk.interceptor.AdminSecurityInterceptor;
import cn.iocoder.mall.spring.boot.constant.RootRequestPath;
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
        AdminSecurityInterceptor.class, AdminAccessLogInterceptor.class}) // 有引入 system-sdk
public class AdminMVCConfiguration implements WebMvcConfigurer {

    @Bean
    @ConditionalOnMissingBean(AdminSecurityInterceptor.class)
    public AdminSecurityInterceptor adminSecurityInterceptor() {
        return new AdminSecurityInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(AdminAccessLogInterceptor.class)
    public AdminAccessLogInterceptor adminAccessLogInterceptor() {
        return new AdminAccessLogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAccessLogInterceptor()).addPathPatterns(RootRequestPath.ADMIN + "/**");
        registry.addInterceptor(adminSecurityInterceptor()).addPathPatterns(RootRequestPath.ADMIN + "/**");
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
