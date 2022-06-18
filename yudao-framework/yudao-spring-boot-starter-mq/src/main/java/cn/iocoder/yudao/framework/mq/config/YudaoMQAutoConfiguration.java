package cn.iocoder.yudao.framework.mq.config;

import cn.iocoder.yudao.framework.mq.core.RedisMQTemplate;
import cn.iocoder.yudao.framework.mq.core.interceptor.RedisMessageInterceptor;
import cn.iocoder.yudao.framework.redis.config.YudaoRedisAutoConfiguration;
import com.alibaba.cloud.stream.binder.rocketmq.convert.RocketMQMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.converter.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息队列配置类
 *
 * @author 芋道源码
 */
@Configuration
@AutoConfigureAfter(YudaoRedisAutoConfiguration.class)
@Slf4j
public class YudaoMQAutoConfiguration {

    @Bean
    public RedisMQTemplate redisMQTemplate(StringRedisTemplate redisTemplate,
                                           List<RedisMessageInterceptor> interceptors) {
        RedisMQTemplate redisMQTemplate = new RedisMQTemplate(redisTemplate);
        // 添加拦截器
        interceptors.forEach(redisMQTemplate::addInterceptor);
        return redisMQTemplate;
    }

    /**
     * 覆盖 {@link RocketMQMessageConverter} 的配置，去掉 fastjson 的转换器，解决不兼容的问题
     */
    @Bean(RocketMQMessageConverter.DEFAULT_NAME)
    @ConditionalOnMissingBean(name = { RocketMQMessageConverter.DEFAULT_NAME })
    public CompositeMessageConverter rocketMQMessageConverter() {
        List<MessageConverter> messageConverters = new ArrayList<>();
        ByteArrayMessageConverter byteArrayMessageConverter = new ByteArrayMessageConverter();
        byteArrayMessageConverter.setContentTypeResolver(null);
        messageConverters.add(byteArrayMessageConverter);
        messageConverters.add(new StringMessageConverter());
        messageConverters.add(new MappingJackson2MessageConverter());
        return new CompositeMessageConverter(messageConverters);
    }

}
