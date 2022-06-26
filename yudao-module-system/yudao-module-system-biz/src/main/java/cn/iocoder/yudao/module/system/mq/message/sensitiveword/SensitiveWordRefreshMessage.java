package cn.iocoder.yudao.module.system.mq.message.sensitiveword;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 敏感词的刷新 Message
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SensitiveWordRefreshMessage extends RemoteApplicationEvent {

    public SensitiveWordRefreshMessage() {
    }

    public SensitiveWordRefreshMessage(Object source, String originService, String destinationService) {
        super(source, originService, destinationService);
    }

}
