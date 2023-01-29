package cn.iocoder.yudao.module.system.mq.consumer.notify;

import cn.iocoder.yudao.module.system.mq.message.notify.NotifyTemplateRefreshMessage;
import cn.iocoder.yudao.module.system.service.notify.NotifyTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link NotifyTemplateRefreshMessage} 的消费者
 *
 * @author xrcoder
 */
@Component
@Slf4j
public class NotifyTemplateRefreshConsumer {

    @Resource
    private NotifyTemplateService notifyTemplateService;

    @EventListener
    public void onMessage(NotifyTemplateRefreshMessage message) {
        log.info("[onMessage][收到 NotifyTemplate 刷新消息]");
        notifyTemplateService.initLocalCache();
    }

}
