package cn.iocoder.mall.product.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Spring Cloud Stream Source 接口
 */
public interface MQStreamProducer {

    /**
     * 商品更新 Output
     */
    String PRODUCT_UPDATE_OUTPUT = "product-update-output";

    @Output(PRODUCT_UPDATE_OUTPUT)
    MessageChannel productUpdateOutput();

//    default boolean sendProductUpdateMessage(ProductUpdateMessage message) {
//        // 创建 Spring Message 对象
//        Message<ProductUpdateMessage> springMessage = MessageBuilder.withPayload(message)
//                .build();
//        // 发送消息
//        return productUpdateOutput().send(springMessage);
//    }

}
