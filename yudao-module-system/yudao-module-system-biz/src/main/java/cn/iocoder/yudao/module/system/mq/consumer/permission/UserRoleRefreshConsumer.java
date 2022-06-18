package cn.iocoder.yudao.module.system.mq.consumer.permission;

import cn.iocoder.yudao.module.system.mq.message.permission.UserRoleRefreshMessage;
import cn.iocoder.yudao.module.system.service.permission.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * 针对 {@link UserRoleRefreshMessage} 的消费者
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class UserRoleRefreshConsumer implements Consumer<UserRoleRefreshMessage> {

    @Resource
    private PermissionService permissionService;

    @Override
    public void accept(UserRoleRefreshMessage userRoleRefreshMessage) {
        log.info("[accept][收到 User 与 Role 的关联刷新消息]");
        permissionService.initLocalCache();
    }
}
