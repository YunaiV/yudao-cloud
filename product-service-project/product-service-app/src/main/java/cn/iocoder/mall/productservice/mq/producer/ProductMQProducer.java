package cn.iocoder.mall.productservice.mq.producer;

import cn.iocoder.mall.productservice.mq.producer.message.ProductUpdateMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductMQProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendProductUpdateMessage(Integer id) {
        // TODO 芋艿：后续优化下，考虑下一致性
        try {
            SendResult sendResult = rocketMQTemplate.syncSend(ProductUpdateMessage.TOPIC, new ProductUpdateMessage().setId(id));
            if (!SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
                log.error("[sendProductUpdateMessage][product({}) 发送更新消息失败，结果为({})]", id, sendResult);
            }
        } catch (Throwable throwable) {
            log.error("[sendProductUpdateMessage][product({}) 发送更新消息失败，发生异常]", id, throwable);
        }
    }

}
