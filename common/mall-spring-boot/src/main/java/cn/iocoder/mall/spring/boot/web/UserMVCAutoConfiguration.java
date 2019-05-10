package cn.iocoder.mall.spring.boot.web;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.servlet.CorsFilter;
import cn.iocoder.mall.spring.boot.web.interceptor.AccessLogInterceptor;
import cn.iocoder.mall.spring.boot.web.handler.GlobalExceptionHandler;
import cn.iocoder.mall.spring.boot.web.handler.GlobalResponseBodyHandler;
import cn.iocoder.mall.user.sdk.interceptor.UserSecurityInterceptor;
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
@ConditionalOnClass({DispatcherServlet.class, WebMvcConfigurer.class, // 有 Spring MVC 容器
        UserSecurityInterceptor.class, // 有引入 user-sdk
        AccessLogInterceptor.class}) // 有引入 system-sdk
public class UserMVCAutoConfiguration implements WebMvcConfigurer {

    @Bean
//    @ConditionalOnMissingBean(AccessLogInterceptor.class)
    public AccessLogInterceptor userAccessLogInterceptor() {
        return new AccessLogInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(UserSecurityInterceptor.class)
    public UserSecurityInterceptor userSecurityInterceptor() {
        return new UserSecurityInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(GlobalResponseBodyHandler.class)
    public GlobalResponseBodyHandler globalReturnValueHandler() {
        return new GlobalResponseBodyHandler();
    }

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAccessLogInterceptor()).addPathPatterns(MallConstants.ROOT_PATH_USER + "/**");
        registry.addInterceptor(userSecurityInterceptor()).addPathPatterns(MallConstants.ROOT_PATH_USER + "/**");
    }

    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
