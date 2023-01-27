package cn.iocoder.yudao.module.system.mq.message.mail;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 邮箱账号的数据刷新 Message
 *
 * @author wangjingyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MailAccountRefreshMessage extends RemoteApplicationEvent {

    public MailAccountRefreshMessage() {
    }

    public MailAccountRefreshMessage(Object source, String originService, String destinationService) {
        super(source, originService, DEFAULT_DESTINATION_FACTORY.getDestination(destinationService));
    }

}
