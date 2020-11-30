package cn.iocoder.mall.payservice.job.notify;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.notify.PayNotifyTaskDO;
import cn.iocoder.mall.payservice.dal.mysql.mapper.notify.PayNotifyTaskMapper;
import cn.iocoder.mall.payservice.service.notify.PayNotifyService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 支付通知重试 Job
 *
 * 由于 RocketMQ 不支持指定时间的延迟消息，所以我们需要通过 Job 扫描到达 {@link PayNotifyTaskDO#getNextNotifyTime()} 时间的任务。
 * 扫描到后，通过发送 MQ 去异步通知，提高通知效率。
 *
 * 考虑到 MQ 执行可能存在延迟的情况，导致一个 {@link PayNotifyTaskDO} 同时触发多个通知，通过 {@link PayNotifyTaskDO#getActive()} 标记解决。
 */
@Component
@Slf4j
public class PayNotifyRetryJob extends IJobHandler {

    @Autowired
    private PayNotifyTaskMapper payNotifyTaskMapper;

    @Autowired
    private PayNotifyService payNotifyService;

    @Override
    @XxlJob("payNotifyRetryJob")
    public ReturnT<String> execute(String param) {
        // 获得需要通知的任务
        List<PayNotifyTaskDO> notifyTasks = payNotifyTaskMapper.selectListByNotify();

        // 循环任务，发送通知
        for (PayNotifyTaskDO notifyTask : notifyTasks) {
            // 发送 MQ
            payNotifyService.sendNotifyMessage(notifyTask);

            // 标记任务执行中。考虑到 MQ 可能会存在先于该操作执行完，所以更新时，增加一个 notifyTimes 作为额外条件，避免覆盖更新的问题。
            PayNotifyTaskDO updateNotifyTask = new PayNotifyTaskDO().setId(notifyTask.getId()).setActive(true);
            payNotifyTaskMapper.update(updateNotifyTask, notifyTask.getNotifyTimes());
        }
        return new ReturnT<>("执行通知数：" + notifyTasks.size());
    }

}
