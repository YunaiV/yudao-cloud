package cn.iocoder.yudao.framework.mq.config;

import com.alibaba.cloud.stream.binder.rocketmq.convert.RocketMQMessageConverter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息队列配置类
 *
 * @author 芋道源码
 */
@AutoConfiguration
public class YudaoMQAutoConfiguration {

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
