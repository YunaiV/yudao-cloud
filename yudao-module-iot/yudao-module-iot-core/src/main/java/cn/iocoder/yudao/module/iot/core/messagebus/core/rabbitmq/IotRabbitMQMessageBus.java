package cn.iocoder.yudao.module.iot.core.messagebus.core.rabbitmq;

import cn.hutool.core.util.TypeUtil;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.iot.core.messagebus.core.IotMessageBus;
import cn.iocoder.yudao.module.iot.core.messagebus.core.IotMessageSubscriber;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import javax.annotation.PreDestroy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于 RabbitMQ 的 {@link IotMessageBus} 实现类
 *
 * @author ywc
 */
@Slf4j
@RequiredArgsConstructor
public class IotRabbitMQMessageBus implements IotMessageBus {

    private final RabbitTemplate rabbitTemplate;

    private final RabbitAdmin rabbitAdmin;

    @Getter
    private final List<IotMessageSubscriber<?>> subscribers = new ArrayList<>();

    private final List<SimpleMessageListenerContainer> containers = new ArrayList<>();

    @Override
    public void post(String topic, Object message) {
        String json = JsonUtils.toJsonString(message);
        rabbitTemplate.send(topic, "#", new Message(json.getBytes()));
        log.info("[post][topic({}) 发送消息({})]", topic, message);
    }

    @Override
    public void register(IotMessageSubscriber<?> subscriber) {
        Type type = TypeUtil.getTypeArgument(subscriber.getClass(), 0);
        if (type == null) {
            throw new IllegalStateException(String.format("类型(%s) 需要设置消息类型", getClass().getName()));
        }

        String topic = subscriber.getTopic();
        String group = subscriber.getGroup();

        Queue queue = new Queue(group, true, false, false);
        rabbitAdmin.declareQueue(queue);

        TopicExchange exchange = new TopicExchange(topic);
        rabbitAdmin.declareExchange(exchange);

        Binding binding = BindingBuilder.bind(queue).to(exchange).with("#");
        rabbitAdmin.declareBinding(binding);

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitTemplate.getConnectionFactory());
        container.setQueues(queue);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(10);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            String body = new String(message.getBody());
            try {
                subscriber.onMessage(JsonUtils.parseObject(body, type));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception ex) {
                log.error("[onMessage][topic({}/{}) message({}) 处理异常]", topic, group, body, ex);
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
        });

        container.start();

        containers.add(container);
        subscribers.add(subscriber);
    }

    @PreDestroy
    public void destroy() {
        for (SimpleMessageListenerContainer container : containers) {
            try {
                container.stop();
                container.destroy();
                log.info("[destroy][关闭 RabbitMQ 消费者容器成功]");
            } catch (Exception e) {
                log.error("[destroy]关闭 RabbitMQ 消费者容器异常]", e);
            }
        }
    }

}
