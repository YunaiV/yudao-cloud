package cn.iocoder.yudao.module.bpm.api.event;

import cn.iocoder.yudao.module.bpm.framework.flowable.core.util.BpmHttpRequestUtils;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

/**
 * 回款审批的结果的监听器实现类
 *
 * @author 芋道源码
 */
public class CrmReceivableStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private RestTemplate loadBalancedRestTemplate;

    @Override
    public String getProcessDefinitionKey() {
        return "crm-receivable-audit";
    }

    @Override
    public void onEvent(@RequestBody @Valid BpmProcessInstanceStatusEvent event) {
        BpmHttpRequestUtils.executeBpmHttpRequest(event,
                "http://crm-server/rpc-api/crm/receivable/update-audit-status",
                loadBalancedRestTemplate);
    }

}
