package cn.iocoder.mall.web.config;

import cn.iocoder.common.framework.servlet.CorsFilter;
import cn.iocoder.mall.web.core.constant.CommonMallConstants;
import cn.iocoder.mall.web.core.handler.GlobalExceptionHandler;
import cn.iocoder.mall.web.core.handler.GlobalResponseBodyHandler;
import cn.iocoder.mall.web.core.interceptor.AccessLogInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CommonWebAutoConfiguration implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // ========== 全局处理器 ==========

    @Bean
    @ConditionalOnMissingBean(GlobalResponseBodyHandler.class)
    public GlobalResponseBodyHandler globalResponseBodyHandler() {
        return new GlobalResponseBodyHandler();
    }

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    // ========== 拦截器相关 ==========

    @Bean
    @ConditionalOnClass(name = {"cn.iocoder.mall.system.rpc.api.systemlog.SystemLogRPC", "org.apache.dubbo.config.annotation.Reference"})
    @ConditionalOnMissingBean(AccessLogInterceptor.class)
    public AccessLogInterceptor accessLogInterceptor() {
        return new AccessLogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            registry.addInterceptor(this.accessLogInterceptor())
                    .addPathPatterns(CommonMallConstants.ROOT_PATH_ADMIN + "/**", CommonMallConstants.ROOT_PATH_USER + "/**");
            logger.info("[addInterceptors][加载 AccessLogInterceptor 拦截器完成]");
        } catch (NoSuchBeanDefinitionException e) {
            logger.warn("[addInterceptors][无法获取 AccessLogInterceptor 拦截器，因此不启动 AccessLog 的记录]");
        }
    }

    // ========== 过滤器相关 ==========

    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
