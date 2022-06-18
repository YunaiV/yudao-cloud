package cn.iocoder.yudao.module.system.mq.consumer.permission;

import cn.iocoder.yudao.framework.mq.core.pubsub.AbstractChannelMessageListener;
import cn.iocoder.yudao.module.system.mq.message.permission.RoleMenuRefreshMessage;
import cn.iocoder.yudao.module.system.service.permission.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * 针对 {@link RoleMenuRefreshMessage} 的消费者
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class RoleMenuRefreshConsumer implements Consumer<RoleMenuRefreshMessage> {

    @Resource
    private PermissionService permissionService;

    @Override
    public void accept(RoleMenuRefreshMessage roleMenuRefreshMessage) {
        log.info("[accept][收到 Role 与 Menu 的关联刷新消息]");
        permissionService.initLocalCache();
    }
}
