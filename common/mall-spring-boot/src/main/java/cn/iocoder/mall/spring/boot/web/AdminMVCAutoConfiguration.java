package cn.iocoder.mall.spring.boot.web;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.servlet.CorsFilter;
import cn.iocoder.mall.system.sdk.interceptor.AdminDemoInterceptor;
import cn.iocoder.mall.spring.boot.web.interceptor.AccessLogInterceptor;
import cn.iocoder.mall.system.sdk.interceptor.AdminSecurityInterceptor;
import cn.iocoder.mall.spring.boot.web.handler.GlobalExceptionHandler;
import cn.iocoder.mall.spring.boot.web.handler.GlobalResponseBodyHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // TODO 芋艿，未来可能考虑 REACTIVE
@ConditionalOnClass({
        DispatcherServlet.class,
        WebMvcConfigurer.class, // 有 Spring MVC 容器
        AdminSecurityInterceptor.class,
        AccessLogInterceptor.class
}) // 有引入 system-sdk
public class AdminMVCAutoConfiguration implements WebMvcConfigurer {

    @Bean
    @ConditionalOnMissingBean(AdminSecurityInterceptor.class)
    public AdminSecurityInterceptor adminSecurityInterceptor() {
        return new AdminSecurityInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(AdminDemoInterceptor.class)
    public AdminDemoInterceptor adminDemoInterceptor() {
        return new AdminDemoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAccessLogInterceptor()).addPathPatterns(MallConstants.ROOT_PATH_ADMIN + "/**");
        registry.addInterceptor(adminSecurityInterceptor()).addPathPatterns(MallConstants.ROOT_PATH_ADMIN + "/**");
        registry.addInterceptor(adminDemoInterceptor()).addPathPatterns(MallConstants.ROOT_PATH_ADMIN + "/**");
    }

}
