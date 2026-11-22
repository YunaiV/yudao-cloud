package cn.iocoder.yudao.module.infra.api.sse;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.sse.core.sender.SseMessageSender;
import cn.iocoder.yudao.module.infra.api.sse.dto.SseSendReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class SseSenderApiImpl implements SseSenderApi {

    @Autowired(required = false) // 由于 yudao.sse.enable 配置项默认关闭 SSE 的功能，所以这里只能不强制注入
    private SseMessageSender sseMessageSender;

    @Override
    public CommonResult<Boolean> send(SseSendReqDTO message) {
        if (sseMessageSender == null) {
            return success(true);
        }
        if (StrUtil.isNotEmpty(message.getConnectionId())) {
            sseMessageSender.send(message.getConnectionId(),
                    message.getMessageType(), message.getMessageContent());
        } else if (message.getUserType() != null && message.getUserId() != null) {
            sseMessageSender.send(message.getUserType(), message.getUserId(),
                    message.getMessageType(), message.getMessageContent());
        } else if (message.getUserType() != null) {
            sseMessageSender.send(message.getUserType(),
                    message.getMessageType(), message.getMessageContent());
        }
        return success(true);
    }

}
