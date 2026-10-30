package cn.iocoder.yudao.module.oa.job.meetingroom;

import cn.hutool.core.util.StrUtil;
import com.xxl.job.core.handler.annotation.XxlJob;
import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.oa.service.meetingroom.OaMeetingRoomBookingService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * OA 会议室过期预定取消 Job
 *
 * 取消已过结束时间、审批通过但尚未开始的会议预定
 *
 * @author 芋道源码
 */
@Component
public class OaMeetingRoomBookingCancelExpiredJob {

    @Resource
    private OaMeetingRoomBookingService meetingRoomBookingService;

    @XxlJob("oaMeetingRoomBookingCancelExpiredJob")
    @TenantJob
    public String execute() {
        int count = meetingRoomBookingService.cancelExpiredMeetingRoomBookings();
        return StrUtil.format("取消过期未开始会议 {} 条", count);
    }

}
