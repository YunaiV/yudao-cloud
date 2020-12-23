package cn.iocoder.mall.dubbo.config;

import cn.iocoder.mall.dubbo.core.web.DubboRouterTagWebInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class DubboWebAutoConfiguration implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(DubboWebAutoConfiguration.class);

    // ========== 拦截器相关 ==========

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            // 设置为 -1000 的原因，保证在比较前面就处理该逻辑。例如说，认证拦截器；
            registry.addInterceptor(new DubboRouterTagWebInterceptor()).order(-1000);
            logger.info("[addInterceptors][加载 DubboRouterTagWebInterceptor 拦截器完成]");
        } catch (NoSuchBeanDefinitionException e) {
            logger.warn("[addInterceptors][无法获取 DubboRouterTagWebInterceptor 拦截器，无法使用基于 dubbo-tag 请求头进行 Dubbo 标签路由]");
        }
    }

}
