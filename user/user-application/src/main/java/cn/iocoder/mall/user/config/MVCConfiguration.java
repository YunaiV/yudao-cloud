package cn.iocoder.mall.user.config;

import cn.iocoder.common.framework.config.GlobalExceptionHandler;
import cn.iocoder.common.framework.config.GlobalResponseBodyAdvice;
import cn.iocoder.mall.user.sdk.interceptor.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@Import(value = {GlobalResponseBodyAdvice.class, GlobalExceptionHandler.class,  // 统一全局返回
        SecurityInterceptor.class}) // 安全拦截器，实现认证和授权功能。
public class MVCConfiguration implements WebMvcConfigurer {

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor);
    }

}