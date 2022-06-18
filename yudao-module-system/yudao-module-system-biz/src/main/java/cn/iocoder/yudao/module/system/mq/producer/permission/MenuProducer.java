package cn.iocoder.yudao.module.system.mq.producer.permission;

import cn.iocoder.yudao.framework.mq.core.bus.AbstractBusProducer;
import cn.iocoder.yudao.module.system.mq.message.permission.MenuRefreshMessage;
import org.springframework.stereotype.Component;

/**
 * Menu 菜单相关消息的 Producer
 */
@Component
public class MenuProducer extends AbstractBusProducer {

    /**
     * 发送 {@link MenuRefreshMessage} 消息
     */
    public void sendMenuRefreshMessage() {
        publishEvent(new MenuRefreshMessage(this, getBusId(), selfDestinationService()));
    }

}
