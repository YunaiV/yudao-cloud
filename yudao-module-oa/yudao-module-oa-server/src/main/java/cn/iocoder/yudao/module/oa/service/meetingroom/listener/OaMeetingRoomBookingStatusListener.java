package cn.iocoder.yudao.module.oa.service.meetingroom.listener;

import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEvent;
import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEventListener;
import cn.iocoder.yudao.module.oa.enums.BpmModelConstants;
import cn.iocoder.yudao.module.oa.service.meetingroom.OaMeetingRoomBookingService;
import jakarta.annotation.Resource;
import cn.iocoder.yudao.module.oa.enums.ApiConstants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会议室预定审批结果监听器
 *
 * @author 芋道源码
 */
@RestController
@Validated
public class OaMeetingRoomBookingStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private OaMeetingRoomBookingService meetingRoomBookingService;

    @Override
    public String getProcessDefinitionKey() {
        return BpmModelConstants.MEETING_ROOM_BOOKING;
    }

    @Override
    @PostMapping(ApiConstants.PREFIX + "/meeting-room-booking/update-audit-status") // 提供给 bpm-server RPC 调用
    protected void onEvent(@RequestBody BpmProcessInstanceStatusEvent event) {
        meetingRoomBookingService.updateMeetingRoomBookingStatus(Long.parseLong(event.getBusinessKey()), event.getStatus());
    }
}
