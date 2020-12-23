package cn.iocoder.mall.sentry.config;

import cn.iocoder.mall.sentry.resolver.DoNothingExceptionResolver;
import io.sentry.spring.SentryExceptionResolver;
import io.sentry.spring.autoconfigure.SentryAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * 自定义的 Sentry 自动配置类
 *
 * @author Hccake 2020/8/6
 * @version 1.0
 */
@ConditionalOnClass({HandlerExceptionResolver.class, SentryExceptionResolver.class})
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "sentry.enabled", havingValue = "true", matchIfMissing = true)
@Configuration(proxyBeanMethods = false)
public class CustomSentryAutoConfiguration {

    /**
     * 用于覆盖原有的 SentryStarter 提供的 SentryExceptionResolver 操作
     * 解决使用 log appender 形式推送错误信息与全局异常捕获导致重复推送的情况
     *
     * @return DoNothingExceptionResolver
     */
    @Bean
    @ConditionalOnClass(SentryAutoConfiguration.class)
    @ConditionalOnMissingBean(SentryExceptionResolver.class)
    public SentryExceptionResolver doNothingExceptionResolver() {
        return new DoNothingExceptionResolver();
    }

}
