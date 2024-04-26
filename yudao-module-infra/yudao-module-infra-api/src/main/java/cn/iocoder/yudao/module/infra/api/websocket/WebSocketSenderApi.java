package cn.iocoder.yudao.module.infra.api.websocket;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.infra.api.websocket.dto.WebSocketSendReqDTO;
import cn.iocoder.yudao.module.infra.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - WebSocket 发送器的") // 对 WebSocketMessageSender 进行封装，提供给其它模块使用
public interface WebSocketSenderApi {

    String PREFIX = ApiConstants.PREFIX + "/websocket";

    @PostMapping(PREFIX + "/send")
    @Operation(summary = "发送 WebSocket 消息")
    CommonResult<Boolean> send(@Valid @RequestBody WebSocketSendReqDTO message);

    /**
     * 发送消息给指定用户
     *
     * @param userType 用户类型
     * @param userId 用户编号
     * @param messageType 消息类型
     * @param messageContent 消息内容，JSON 格式
     */
    default void send(Integer userType, Long userId, String messageType, String messageContent) {
        send(new WebSocketSendReqDTO().setUserType(userType).setUserId(userId)
                .setMessageType(messageType).setMessageContent(messageContent)).checkError();
    }

    /**
     * 发送消息给指定用户类型
     *
     * @param userType 用户类型
     * @param messageType 消息类型
     * @param messageContent 消息内容，JSON 格式
     */
    default void send(Integer userType, String messageType, String messageContent) {
        send(new WebSocketSendReqDTO().setUserType(userType)
                .setMessageType(messageType).setMessageContent(messageContent)).checkError();
    }

    /**
     * 发送消息给指定 Session
     *
     * @param sessionId Session 编号
     * @param messageType 消息类型
     * @param messageContent 消息内容，JSON 格式
     */
    default void send(String sessionId, String messageType, String messageContent) {
        send(new WebSocketSendReqDTO().setSessionId(sessionId)
                .setMessageType(messageType).setMessageContent(messageContent)).checkError();
    }

    default void sendObject(Integer userType, Long userId, String messageType, Object messageContent) {
        send(userType, userId, messageType, JsonUtils.toJsonString(messageContent));
    }

    default void sendObject(Integer userType, String messageType, Object messageContent) {
        send(userType, messageType, JsonUtils.toJsonString(messageContent));
    }

    default void sendObject(String sessionId, String messageType, Object messageContent) {
        send(sessionId, messageType, JsonUtils.toJsonString(messageContent));
    }

}
