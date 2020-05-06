package cn.iocoder.mall.product.biz.config;

import cn.iocoder.mall.product.biz.message.MQStreamProducer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(MQStreamProducer.class)
public class MQStreamConfiguration {
}
