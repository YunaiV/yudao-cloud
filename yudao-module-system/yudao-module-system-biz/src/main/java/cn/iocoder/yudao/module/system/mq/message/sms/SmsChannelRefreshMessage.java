package cn.iocoder.yudao.module.system.mq.message.sms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 短信渠道的数据刷新 Message
 *
 * @author 芋道源码
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmsChannelRefreshMessage extends RemoteApplicationEvent {

    public SmsChannelRefreshMessage() {
    }

    public SmsChannelRefreshMessage(Object source, String originService, String destinationService) {
        super(source, originService, destinationService);
    }

}
