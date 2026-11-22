package cn.iocoder.yudao.module.infra.api.sse;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.infra.api.sse.dto.SseSendReqDTO;
import cn.iocoder.yudao.module.infra.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - SSE 发送器") // 对 SseMessageSender 进行封装，提供给其它模块使用
public interface SseSenderApi {

    String PREFIX = ApiConstants.PREFIX + "/sse";

    @PostMapping(PREFIX + "/send")
    @Operation(summary = "发送 SSE 消息")
    CommonResult<Boolean> send(@Valid @RequestBody SseSendReqDTO message);

    /**
     * 发送消息给指定用户
     *
     * @param userType 用户类型
     * @param userId 用户编号
     * @param messageType 消息类型，作为 SSE 的 event 名
     * @param messageContent 消息内容，JSON 格式
     */
    default void send(Integer userType, Long userId, String messageType, String messageContent) {
        send(new SseSendReqDTO().setUserType(userType).setUserId(userId)
                .setMessageType(messageType).setMessageContent(messageContent)).checkError();
    }

    /**
     * 发送消息给指定用户类型
     *
     * @param userType 用户类型
     * @param messageType 消息类型，作为 SSE 的 event 名
     * @param messageContent 消息内容，JSON 格式
     */
    default void send(Integer userType, String messageType, String messageContent) {
        send(new SseSendReqDTO().setUserType(userType)
                .setMessageType(messageType).setMessageContent(messageContent)).checkError();
    }

    /**
     * 发送消息给指定 SSE 连接
     *
     * @param connectionId SSE 连接编号，以 sse: 开头
     * @param messageType 消息类型，作为 SSE 的 event 名
     * @param messageContent 消息内容，JSON 格式
     */
    default void send(String connectionId, String messageType, String messageContent) {
        send(new SseSendReqDTO().setConnectionId(connectionId)
                .setMessageType(messageType).setMessageContent(messageContent)).checkError();
    }

    default void sendObject(Integer userType, Long userId, String messageType, Object messageContent) {
        send(userType, userId, messageType, JsonUtils.toJsonString(messageContent));
    }

    default void sendObject(Integer userType, String messageType, Object messageContent) {
        send(userType, messageType, JsonUtils.toJsonString(messageContent));
    }

    default void sendObject(String connectionId, String messageType, Object messageContent) {
        send(connectionId, messageType, JsonUtils.toJsonString(messageContent));
    }

}
