package cn.iocoder.yudao.module.system.mq.message.notify;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 站内信模板的数据刷新 Message
 *
 * @author xrcoder
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NotifyTemplateRefreshMessage extends RemoteApplicationEvent {

    public NotifyTemplateRefreshMessage() {
    }

    public NotifyTemplateRefreshMessage(Object source, String originService, String destinationService) {
        super(source, originService, DEFAULT_DESTINATION_FACTORY.getDestination(destinationService));
    }

}
