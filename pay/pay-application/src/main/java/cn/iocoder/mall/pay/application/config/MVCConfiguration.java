package cn.iocoder.mall.pay.application.config;

import cn.iocoder.common.framework.config.GlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@Import(value = {GlobalExceptionHandler.class,  // 统一全局返回
//        AdminSecurityInterceptor.class
})
public class MVCConfiguration implements WebMvcConfigurer {

//    @Autowired
//    private UserSecurityInterceptor securityInterceptor;

//    @Autowired
//    private AdminSecurityInterceptor adminSecurityInterceptor;
////
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(securityInterceptor).addPathPatterns("/user/**", "/admin/**"); // 只拦截我们定义的接口
//        registry.addInterceptor(adminSecurityInterceptor).addPathPatterns("/admins/**")
//                .excludePathPatterns("/admins/passport/login"); // 排除登陆接口
//    }

    // TODO 芋艿，允许跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

}
