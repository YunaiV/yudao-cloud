package cn.iocoder.yudao.module.bpm.api.event;

import cn.iocoder.yudao.module.bpm.framework.flowable.core.util.BpmHttpRequestUtils;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 合同审批的结果的监听器实现类
 *
 * @author 芋道源码
 */
public class CrmContractStatusListener extends BpmProcessInstanceStatusEventListener {

    @Override
    public String getProcessDefinitionKey() {
        return "crm-contract-audit";
    }

    @Override
    public void onEvent(@RequestBody @Valid BpmProcessInstanceStatusEvent event) {
        BpmHttpRequestUtils.executeBpmHttpRequest(event,
                "http://crm-server/rpc-api/crm/contract/update-audit-status");
    }

}
