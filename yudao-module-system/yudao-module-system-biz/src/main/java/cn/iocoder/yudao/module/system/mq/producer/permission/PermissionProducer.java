package cn.iocoder.yudao.module.system.mq.producer.permission;

import cn.iocoder.yudao.module.system.mq.message.permission.RoleMenuRefreshMessage;
import cn.iocoder.yudao.framework.mq.core.RedisMQTemplate;
import cn.iocoder.yudao.module.system.mq.message.permission.UserRoleRefreshMessage;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Permission 权限相关消息的 Producer
 */
@Component
public class PermissionProducer {

    @Resource
    private StreamBridge streamBridge;

    /**
     * 发送 {@link RoleMenuRefreshMessage} 消息
     */
    public void sendRoleMenuRefreshMessage() {
        RoleMenuRefreshMessage message = new RoleMenuRefreshMessage();
        streamBridge.send("roleMenuRefresh-out-0", message);
    }

    /**
     * 发送 {@link UserRoleRefreshMessage} 消息
     */
    public void sendUserRoleRefreshMessage() {
        UserRoleRefreshMessage message = new UserRoleRefreshMessage();
        streamBridge.send("userRoleRefresh-out-0", message);
    }

}
