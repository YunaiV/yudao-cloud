package cn.iocoder.yudao.module.system.mq.message.permission;

import lombok.Data;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 角色数据刷新 Message
 *
 * @author 芋道源码
 */
@Data
public class RoleRefreshMessage extends RemoteApplicationEvent {

    public RoleRefreshMessage() {
    }

    public RoleRefreshMessage(Object source, String originService, String destinationService) {
        super(source, originService, destinationService);
    }

}
