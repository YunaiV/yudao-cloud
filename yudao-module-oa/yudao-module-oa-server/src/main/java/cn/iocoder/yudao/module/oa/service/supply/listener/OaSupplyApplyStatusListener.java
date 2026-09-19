package cn.iocoder.yudao.module.oa.service.supply.listener;

import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEvent;
import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEventListener;
import cn.iocoder.yudao.module.oa.enums.BpmModelConstants;
import cn.iocoder.yudao.module.oa.service.supply.OaSupplyApplyService;
import jakarta.annotation.Resource;
import cn.iocoder.yudao.module.oa.enums.ApiConstants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// TODO DONE @AI：审批监听器统一放在业务模块的 listener 子包
/**
 * 用品申请审批结果监听器
 *
 * @author 芋道源码
 */
@RestController
@Validated
public class OaSupplyApplyStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private OaSupplyApplyService supplyApplyService;

    @Override
    public String getProcessDefinitionKey() {
        return BpmModelConstants.SUPPLY_APPLY;
    }

    @Override
    @PostMapping(ApiConstants.PREFIX + "/supply-apply/update-audit-status") // 提供给 bpm-server RPC 调用
    protected void onEvent(@RequestBody BpmProcessInstanceStatusEvent event) {
        supplyApplyService.updateSupplyApplyStatus(Long.parseLong(event.getBusinessKey()), event.getId(), event.getStatus());
    }

}
