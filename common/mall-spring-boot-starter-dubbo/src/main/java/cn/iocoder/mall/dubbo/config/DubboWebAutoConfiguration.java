package cn.iocoder.mall.dubbo.config;

import cn.iocoder.mall.dubbo.core.web.DubboRouterTagWebInterceptor;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class DubboWebAutoConfiguration implements WebMvcConfigurer {

    // ========== 拦截器相关 ==========

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            registry.addInterceptor(new DubboRouterTagWebInterceptor()).order(-1000);
        } catch (NoSuchBeanDefinitionException e) {
//            logger.warn("[addInterceptors][无法获取 AccessLogInterceptor 拦截器，因此不启动 AccessLog 的记录]");
        }
    }

}
