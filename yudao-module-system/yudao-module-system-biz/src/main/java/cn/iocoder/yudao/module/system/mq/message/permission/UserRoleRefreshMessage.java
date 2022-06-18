package cn.iocoder.yudao.module.system.mq.message.permission;

import lombok.Data;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 用户与角色的数据刷新 Message
 *
 * @author 芋道源码
 */
@Data
public class UserRoleRefreshMessage extends RemoteApplicationEvent {

    public UserRoleRefreshMessage() {
    }

    public UserRoleRefreshMessage(Object source, String originService, String destinationService) {
        super(source, originService, destinationService);
    }

}
