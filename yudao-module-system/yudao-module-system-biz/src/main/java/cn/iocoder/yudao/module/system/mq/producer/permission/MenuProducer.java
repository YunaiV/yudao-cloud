package cn.iocoder.yudao.module.system.mq.producer.permission;

import cn.iocoder.yudao.module.system.mq.message.permission.MenuRefreshMessage;
import cn.iocoder.yudao.framework.mq.core.RedisMQTemplate;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Menu 菜单相关消息的 Producer
 */
@Component
public class MenuProducer {

    @Resource
    private StreamBridge streamBridge;

    /**
     * 发送 {@link MenuRefreshMessage} 消息
     */
    public void sendMenuRefreshMessage() {
        MenuRefreshMessage message = new MenuRefreshMessage();
        streamBridge.send("demo02-output", message);
    }

}
