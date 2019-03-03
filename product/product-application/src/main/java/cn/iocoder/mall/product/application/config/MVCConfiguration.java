package cn.iocoder.mall.product.application.config;

import cn.iocoder.common.framework.config.GlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@Import(value = {GlobalExceptionHandler.class}) // 统一全局返回
public class MVCConfiguration implements WebMvcConfigurer {

//    @Autowired
//    private SecurityInterceptor securityInterceptor;

//    @Reference
//    private OAuth2Service oauth2Service;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(securityInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决 swagger-ui.html 的访问，参考自 https://stackoverflow.com/questions/43545540/swagger-ui-no-mapping-found-for-http-request 解决
        registry.addResourceHandler("swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}