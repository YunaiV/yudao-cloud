package cn.iocoder.mall.web.config;

import cn.iocoder.mall.web.core.handler.GlobalExceptionHandler;
import cn.iocoder.mall.web.core.handler.GlobalResponseBodyHandler;
import cn.iocoder.mall.web.core.interceptor.AccessLogInterceptor;
import cn.iocoder.mall.web.core.servlet.CorsFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

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
    @ConditionalOnClass(name = {"cn.iocoder.mall.systemservice.rpc.systemlog.SystemExceptionLogRpc", "org.apache.dubbo.config.annotation.Reference"})
    @ConditionalOnMissingBean(AccessLogInterceptor.class)
    public AccessLogInterceptor accessLogInterceptor() {
        return new AccessLogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            registry.addInterceptor(this.accessLogInterceptor());
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

    // ========== MessageConverter 相关 ==========

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建 FastJsonHttpMessageConverter 对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // 自定义 FastJson 配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.defaultCharset()); // 设置字符集
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect, // 剔除循环引用
                SerializerFeature.WriteNonStringKeyAsString); // 解决 Integer 作为 Key 时，转换为 String 类型，避免浏览器报错
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        // 设置支持的 MediaType
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        // 添加到 converters 中
        converters.add(0, fastJsonHttpMessageConverter); // 注意，添加到最开头，放在 MappingJackson2XmlHttpMessageConverter 前面
    }

}
