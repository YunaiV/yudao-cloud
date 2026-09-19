package cn.iocoder.yudao.module.oa.service.officialdoc.listener;

import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEvent;
import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEventListener;
import cn.iocoder.yudao.module.oa.enums.BpmModelConstants;
import cn.iocoder.yudao.module.oa.service.officialdoc.OaOfficialDocSendService;
import jakarta.annotation.Resource;
import cn.iocoder.yudao.module.oa.enums.ApiConstants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公文发文审批结果监听器
 *
 * @author 芋道源码
 */
@RestController
@Validated
public class OaOfficialDocSendStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private OaOfficialDocSendService officialDocSendService;

    @Override
    public String getProcessDefinitionKey() {
        return BpmModelConstants.OFFICIAL_DOC_SEND;
    }

    @Override
    @PostMapping(ApiConstants.PREFIX + "/official-doc-send/update-audit-status") // 提供给 bpm-server RPC 调用
    protected void onEvent(@RequestBody BpmProcessInstanceStatusEvent event) {
        officialDocSendService.updateOfficialDocSendStatus(Long.parseLong(event.getBusinessKey()), event.getId(), event.getStatus());
    }

}
