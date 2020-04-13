package cn.iocoder.mall.product.config;

import cn.iocoder.mall.product.message.MQStreamProducer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(MQStreamProducer.class)
public class MQStreamConfiguration {
}
