package cn.iocoder.mall.web.config;

import cn.iocoder.mall.web.interceptor.AccessLogInterceptor;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // TODO 芋艿，未来可能考虑 REACTIVE
public class CommonWebAutoConfiguration implements WebMvcConfigurer {

    @Reference(validation = "true", version = "${dubbo.consumer.AdminAccessLogService.version:1.0.0}")
//    @Reference(validation = "true", version = "1.0.0")
//    private SystemLogRPC systemLogRPC;

    @Bean
    public AccessLogInterceptor accessLogInterceptor() {
        return new AccessLogInterceptor();
    }

}
