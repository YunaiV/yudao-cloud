package cn.iocoder.yudao.module.system.mq.producer.permission;

import cn.iocoder.yudao.framework.mq.core.bus.AbstractBusProducer;
import cn.iocoder.yudao.module.system.mq.message.permission.RoleMenuRefreshMessage;
import cn.iocoder.yudao.module.system.mq.message.permission.UserRoleRefreshMessage;
import org.springframework.stereotype.Component;

/**
 * Permission 权限相关消息的 Producer
 */
@Component
public class PermissionProducer extends AbstractBusProducer {

    /**
     * 发送 {@link RoleMenuRefreshMessage} 消息
     */
    public void sendRoleMenuRefreshMessage() {
        publishEvent(new RoleMenuRefreshMessage(this, getBusId(), selfDestinationService()));
    }

    /**
     * 发送 {@link UserRoleRefreshMessage} 消息
     */
    public void sendUserRoleRefreshMessage() {
        publishEvent(new UserRoleRefreshMessage(this, getBusId(), selfDestinationService()));
    }

}
