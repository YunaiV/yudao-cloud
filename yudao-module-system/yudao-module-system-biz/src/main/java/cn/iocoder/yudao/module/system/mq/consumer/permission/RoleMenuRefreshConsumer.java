package cn.iocoder.yudao.module.system.mq.consumer.permission;

import cn.iocoder.yudao.module.system.mq.message.permission.RoleMenuRefreshMessage;
import cn.iocoder.yudao.module.system.service.permission.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link RoleMenuRefreshMessage} 的消费者
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class RoleMenuRefreshConsumer {

    @Resource
    private PermissionService permissionService;

    @EventListener
    public void execute(RoleMenuRefreshMessage roleMenuRefreshMessage) {
        log.info("[execute][收到 Role 与 Menu 的关联刷新消息]");
        permissionService.initLocalCache();
    }
}
