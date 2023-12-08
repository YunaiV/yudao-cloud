package cn.iocoder.yudao.module.system.api.sms;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.api.sms.dto.send.SmsSendSingleToUserReqDTO;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 短信发送")
public interface SmsSendApi {

    String PREFIX = ApiConstants.PREFIX + "/sms/send";

    @PostMapping(PREFIX + "/send-single-admin")
    @Operation(summary = "发送单条短信给 Admin 用户", description = "在 mobile 为空时，使用 userId 加载对应 Admin 的手机号")
    CommonResult<Long> sendSingleSmsToAdmin(@Valid @RequestBody SmsSendSingleToUserReqDTO reqDTO);

    @PostMapping(PREFIX + "/send-single-member")
    @Operation(summary = "发送单条短信给 Member 用户", description = "在 mobile 为空时，使用 userId 加载对应 Member 的手机号")
    CommonResult<Long> sendSingleSmsToMember(@Valid @RequestBody SmsSendSingleToUserReqDTO reqDTO);

}
