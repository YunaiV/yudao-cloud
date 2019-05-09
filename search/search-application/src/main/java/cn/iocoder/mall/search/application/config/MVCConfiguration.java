package cn.iocoder.mall.search.application.config;

import cn.iocoder.common.framework.config.GlobalExceptionHandler;
import cn.iocoder.common.framework.servlet.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@Import(value = {GlobalExceptionHandler.class,  // 统一全局返回
//        AdminSecurityInterceptor.class, UserAccessLogInterceptor.class,
//        UserSecurityInterceptor.class, AdminAccessLogInterceptor.class,
})
public class MVCConfiguration implements WebMvcConfigurer {

//    @Autowired
//    private UserSecurityInterceptor securityInterceptor;

//    @Autowired
//    private UserSecurityInterceptor userSecurityInterceptor;
//    @Autowired
//    private UserAccessLogInterceptor userAccessLogInterceptor;
//    @Autowired
//    private AdminSecurityInterceptor adminSecurityInterceptor;
//    @Autowired
//    private AdminAccessLogInterceptor adminAccessLogInterceptor;
//
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        // 用户
//        registry.addInterceptor(userAccessLogInterceptor).addPathPatterns("/users/**");
//        registry.addInterceptor(userSecurityInterceptor).addPathPatterns("/users/**"); // 只拦截我们定义的接口
//        // 管理员
//        registry.addInterceptor(adminAccessLogInterceptor).addPathPatterns("/admins/**");
//        registry.addInterceptor(adminSecurityInterceptor).addPathPatterns("/admins/**");
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
