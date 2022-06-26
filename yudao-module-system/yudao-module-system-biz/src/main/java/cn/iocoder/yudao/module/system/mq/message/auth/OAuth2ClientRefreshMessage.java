package cn.iocoder.yudao.module.system.mq.message.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * OAuth 2.0 客户端的数据刷新 Message
 *
 * @author 芋道源码
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OAuth2ClientRefreshMessage extends RemoteApplicationEvent {

    public OAuth2ClientRefreshMessage() {
    }

    public OAuth2ClientRefreshMessage(Object source, String originService, String destinationService) {
        super(source, originService, destinationService);
    }

}
