package cn.iocoder.yudao.module.bpm.api.task;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.bpm.service.task.BpmProcessInstanceService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * Flowable 流程实例 Api 实现类
 *
 * @author 芋道源码
 * @author jason
 */
@RestController
@Validated
public class BpmProcessInstanceApiImpl implements BpmProcessInstanceApi {

    @Resource
    private BpmProcessInstanceService processInstanceService;

    @Override
    public CommonResult<String> createProcessInstance(Long userId, @Valid BpmProcessInstanceCreateReqDTO reqDTO) {
        return success(processInstanceService.createProcessInstance(userId, reqDTO));
    }

}
