package cn.iocoder.mall.payservice.mq.producer;

import cn.iocoder.mall.payservice.mq.producer.message.PayRefundSuccessMessage;
import cn.iocoder.mall.payservice.mq.producer.message.PayTransactionSuccessMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
// TODO 芋艿：后续优化下，考虑下一致性
public class PayMQProducer {

    @Autowired
    private RocketMQTemplate template;

    public void sendPayRefundNotifyTaskMessage(PayRefundSuccessMessage message) {
        try {
            SendResult sendResult = template.syncSend(PayTransactionSuccessMessage.TOPIC, message);
            if (!SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
                log.error("[sendPayRefundNotifyTaskMessage][消息({}) 发送更新消息失败，结果为({})]", message, sendResult);
            }
        } catch (Throwable throwable) {
            log.error("[sendPayRefundNotifyTaskMessage][消息({}) 发送更新消息失败，发生异常]", message, throwable);
        }
    }

    public void sendPayTransactionNotifyTaskMessage(PayTransactionSuccessMessage message) {
        try {
            SendResult sendResult = template.syncSend(PayTransactionSuccessMessage.TOPIC, message);
            if (!SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
                log.error("[sendPayTransactionNotifyTaskMessage][消息({}) 发送更新消息失败，结果为({})]", message, sendResult);
            }
        } catch (Throwable throwable) {
            log.error("[sendPayTransactionNotifyTaskMessage][消息({}) 发送更新消息失败，发生异常]", message, throwable);
        }
    }


}
