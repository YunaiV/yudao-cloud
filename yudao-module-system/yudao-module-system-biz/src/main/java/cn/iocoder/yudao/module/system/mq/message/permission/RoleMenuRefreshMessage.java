package cn.iocoder.yudao.module.system.mq.message.permission;

import lombok.Data;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 角色与菜单数据刷新 Message
 *
 * @author 芋道源码
 */
@Data
public class RoleMenuRefreshMessage extends RemoteApplicationEvent {

    public RoleMenuRefreshMessage() {
    }

    public RoleMenuRefreshMessage(Object source, String originService, String destinationService) {
        super(source, originService, DEFAULT_DESTINATION_FACTORY.getDestination(destinationService));
    }

}
