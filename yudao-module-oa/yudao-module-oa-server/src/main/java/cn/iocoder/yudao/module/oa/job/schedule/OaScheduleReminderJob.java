package cn.iocoder.yudao.module.oa.job.schedule;

import cn.hutool.core.util.StrUtil;
import com.xxl.job.core.handler.annotation.XxlJob;
import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.oa.service.schedule.OaScheduleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * OA 日程提醒 Job
 *
 * @author 芋道源码
 */
@Component
public class OaScheduleReminderJob {

    @Resource
    private OaScheduleService scheduleService;

    @XxlJob("oaScheduleReminderJob")
    @TenantJob
    public String execute() {
        int count = scheduleService.sendScheduleReminders();
        return StrUtil.format("发送日程提醒 {} 条", count);
    }

}
