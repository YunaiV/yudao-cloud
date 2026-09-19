package cn.iocoder.yudao.module.oa.service.resign.listener;

import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEvent;
import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEventListener;
import cn.iocoder.yudao.module.oa.enums.BpmModelConstants;
import cn.iocoder.yudao.module.oa.service.resign.OaResignApplyService;
import jakarta.annotation.Resource;
import cn.iocoder.yudao.module.oa.enums.ApiConstants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 离职申请审批结果监听器
 *
 * @author 芋道源码
 */
@RestController
@Validated
public class OaResignApplyStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private OaResignApplyService resignApplyService;

    @Override
    public String getProcessDefinitionKey() {
        return BpmModelConstants.RESIGN_APPLY;
    }

    @Override
    @PostMapping(ApiConstants.PREFIX + "/resign-apply/update-audit-status") // 提供给 bpm-server RPC 调用
    protected void onEvent(@RequestBody BpmProcessInstanceStatusEvent event) {
        resignApplyService.updateResignApplyStatus(Long.valueOf(event.getBusinessKey()), event.getStatus());
    }

}
