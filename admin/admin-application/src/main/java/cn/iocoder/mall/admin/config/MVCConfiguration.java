package cn.iocoder.mall.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
//@Import(value = {GlobalExceptionHandler.class,  // 统一全局返回
//        ) // TODO 安全拦截器，实现认证和授权功能。
public class MVCConfiguration implements WebMvcConfigurer {

//    @Autowired
//    private UserSecurityInterceptor securityInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(securityInterceptor).addPathPatterns("/user/**", "/admin/**"); // 只拦截我们定义的接口
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决 swagger-ui.html 的访问，参考自 https://stackoverflow.com/questions/43545540/swagger-ui-no-mapping-found-for-http-request 解决
        registry.addResourceHandler("swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}