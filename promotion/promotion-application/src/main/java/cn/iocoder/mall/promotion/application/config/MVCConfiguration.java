package cn.iocoder.mall.promotion.application.config;

import cn.iocoder.common.framework.config.GlobalExceptionHandler;
import cn.iocoder.common.framework.servlet.CorsFilter;
import cn.iocoder.mall.admin.sdk.interceptor.AdminAccessLogInterceptor;
import cn.iocoder.mall.admin.sdk.interceptor.AdminSecurityInterceptor;
import cn.iocoder.mall.user.sdk.interceptor.UserAccessLogInterceptor;
import cn.iocoder.mall.user.sdk.interceptor.UserSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@Import(value = {GlobalExceptionHandler.class,  // 统一全局返回
        AdminSecurityInterceptor.class, UserAccessLogInterceptor.class,
        UserSecurityInterceptor.class, AdminAccessLogInterceptor.class,
})
public class MVCConfiguration implements WebMvcConfigurer {

//    @Autowired
//    private UserSecurityInterceptor securityInterceptor;

    @Autowired
    private UserSecurityInterceptor userSecurityInterceptor;
    @Autowired
    private UserAccessLogInterceptor userAccessLogInterceptor;
    @Autowired
    private AdminSecurityInterceptor adminSecurityInterceptor;
    @Autowired
    private AdminAccessLogInterceptor adminAccessLogInterceptor;
//
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 用户
//        registry.addInterceptor(userAccessLogInterceptor).addPathPatterns("/users/**");
//        registry.addInterceptor(userSecurityInterceptor).addPathPatterns("/users/**"); // 只拦截我们定义的接口
        // 管理员
//        registry.addInterceptor(adminAccessLogInterceptor).addPathPatterns("/admins/**");
//        registry.addInterceptor(adminSecurityInterceptor).addPathPatterns("/admins/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决 swagger-ui.html 的访问，参考自 https://stackoverflow.com/questions/43545540/swagger-ui-no-mapping-found-for-http-request 解决
        registry.addResourceHandler("swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
