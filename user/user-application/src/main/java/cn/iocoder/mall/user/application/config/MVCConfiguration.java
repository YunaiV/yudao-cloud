package cn.iocoder.mall.user.application.config;

import cn.iocoder.common.framework.config.GlobalExceptionHandler;
import cn.iocoder.mall.admin.sdk.interceptor.AdminSecurityInterceptor;
import cn.iocoder.mall.user.sdk.interceptor.UserAccessLogInterceptor;
import cn.iocoder.mall.user.sdk.interceptor.UserSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@Import(value = {GlobalExceptionHandler.class,  // 统一全局返回
        UserSecurityInterceptor.class, AdminSecurityInterceptor.class}) // 安全拦截器，实现认证和授权功能。
public class MVCConfiguration implements WebMvcConfigurer {

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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决 swagger-ui.html 的访问，参考自 https://stackoverflow.com/questions/43545540/swagger-ui-no-mapping-found-for-http-request 解决
        registry.addResourceHandler("swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}