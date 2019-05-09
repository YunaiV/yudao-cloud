package cn.iocoder.mall.order.application.config;

import cn.iocoder.common.framework.config.GlobalExceptionHandler;
import cn.iocoder.common.framework.servlet.CorsFilter;
import cn.iocoder.mall.admin.sdk.interceptor.AdminSecurityInterceptor;
import cn.iocoder.mall.user.sdk.interceptor.UserAccessLogInterceptor;
import cn.iocoder.mall.user.sdk.interceptor.UserSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
@Import(value = {GlobalExceptionHandler.class,  // 统一全局返回
        UserSecurityInterceptor.class, AdminSecurityInterceptor.class,
        UserAccessLogInterceptor.class}) // 安全拦截器，实现认证和授权功能。
public class MVCConfiguration implements WebMvcConfigurer {

//    @Autowired
//    private SecurityInterceptor securityInterceptor;

//    @Reference
//    private OAuth2Service oauth2Service;

    @Autowired
    private UserSecurityInterceptor userSecurityInterceptor;
    @Autowired
    private UserAccessLogInterceptor userAccessLogInterceptor;
    @Autowired
    private AdminSecurityInterceptor adminSecurityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 用户
        registry.addInterceptor(userAccessLogInterceptor).addPathPatterns("/users/**");
        registry.addInterceptor(userSecurityInterceptor).addPathPatterns("/users/**"); // 只拦截我们定义的接口
        // 管理员
        registry.addInterceptor(adminSecurityInterceptor).addPathPatterns("/admins/**"); // 只拦截我们定义的接口
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    // TODO 芋艿，允许跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
}
