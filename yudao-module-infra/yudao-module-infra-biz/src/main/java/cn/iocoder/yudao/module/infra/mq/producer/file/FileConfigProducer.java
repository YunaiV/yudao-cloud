package cn.iocoder.yudao.module.infra.mq.producer.file;

import cn.iocoder.yudao.framework.mq.core.bus.AbstractBusProducer;
import cn.iocoder.yudao.module.infra.mq.message.file.FileConfigRefreshMessage;
import org.springframework.stereotype.Component;

/**
 * 文件配置相关消息的 Producer
 */
@Component
public class FileConfigProducer extends AbstractBusProducer {

    /**
     * 发送 {@link FileConfigRefreshMessage} 消息
     */
    public void sendFileConfigRefreshMessage() {
        publishEvent(new FileConfigRefreshMessage(this, selfDestinationService(), selfDestinationService()));
    }

}
