package cn.iocoder.yudao.module.system.mq.producer.permission;

import cn.iocoder.yudao.module.system.mq.message.permission.RoleRefreshMessage;
import cn.iocoder.yudao.framework.mq.core.RedisMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Role 角色相关消息的 Producer
 *
 * @author 芋道源码
 */
@Component
public class RoleProducer {

    @Resource
    private StreamBridge streamBridge;

    /**
     * 发送 {@link RoleRefreshMessage} 消息
     */
    public void sendRoleRefreshMessage() {
        RoleRefreshMessage message = new RoleRefreshMessage();
        streamBridge.send("demo01-output", message);
    }

}
