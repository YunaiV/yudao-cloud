package cn.iocoder.yudao.module.system.mq.consumer.permission;

import cn.iocoder.yudao.module.system.mq.message.permission.RoleRefreshMessage;
import cn.iocoder.yudao.module.system.service.permission.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * 针对 {@link RoleRefreshMessage} 的消费者
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class RoleRefreshConsumer implements Consumer<RoleRefreshMessage> {

    @Resource
    private RoleService roleService;

    @Override
    public void accept(RoleRefreshMessage message) {
        log.info("[accept][收到 Role 刷新消息]");
        roleService.initLocalCache();
    }

}
