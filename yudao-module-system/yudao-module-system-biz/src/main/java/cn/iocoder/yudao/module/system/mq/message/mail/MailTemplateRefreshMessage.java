package cn.iocoder.yudao.module.system.mq.message.mail;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 邮箱模板的数据刷新 Message
 *
 * @author wangjingyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MailTemplateRefreshMessage extends RemoteApplicationEvent {

    public MailTemplateRefreshMessage() {
    }

    public MailTemplateRefreshMessage(Object source, String originService, String destinationService) {
        super(source, originService, DEFAULT_DESTINATION_FACTORY.getDestination(destinationService));
    }

}
