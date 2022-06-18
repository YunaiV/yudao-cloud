package cn.iocoder.yudao.module.system.mq.consumer.permission;

import cn.iocoder.yudao.module.system.mq.message.permission.MenuRefreshMessage;
import cn.iocoder.yudao.module.system.service.permission.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * 针对 {@link MenuRefreshMessage} 的消费者
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class MenuRefreshConsumer implements Consumer<MenuRefreshMessage> {

    @Resource
    private MenuService menuService;

    @Override
    public void accept(MenuRefreshMessage menuRefreshMessage) {
        log.info("[accept][收到 Menu 刷新消息]");
        menuService.initLocalCache();
    }
}
