package cn.iocoder.yudao.module.system.mq.consumer.dept;

import cn.iocoder.yudao.module.system.mq.message.dept.DeptRefreshMessage;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link DeptRefreshMessage} 的消费者
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class DeptRefreshConsumer {

    @Resource
    private DeptService deptService;

    @EventListener
    public void execute(DeptRefreshMessage message) {
        log.info("[execute][收到 Dept 刷新消息]");
        deptService.initLocalCache();
    }

}
