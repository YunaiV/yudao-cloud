package cn.iocoder.yudao.module.system.mq.consumer.mail;

import cn.iocoder.yudao.module.system.mq.message.mail.MailAccountRefreshMessage;
import cn.iocoder.yudao.module.system.service.mail.MailAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link MailAccountRefreshMessage} 的消费者
 *
 * @author wangjingyi
 */
@Component
@Slf4j
public class MailAccountRefreshConsumer {

    @Resource
    private MailAccountService mailAccountService;

    @EventListener
    public void onMessage(MailAccountRefreshMessage message) {
        log.info("[onMessage][收到 Mail Account 刷新信息]");
        mailAccountService.initLocalCache();
    }

}
