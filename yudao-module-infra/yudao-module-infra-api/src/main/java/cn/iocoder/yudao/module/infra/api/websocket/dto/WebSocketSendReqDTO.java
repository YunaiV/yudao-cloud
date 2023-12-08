package cn.iocoder.yudao.module.infra.api.websocket.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Schema(description = "RPC 服务 - WebSocket 消息发送 Request DTO")
@Data
public class WebSocketSendReqDTO {

    @Schema(description = "Session 编号", example = "abc")
    private String sessionId;
    @Schema(description = "用户编号", example = "1024")
    private Long userId;
    @Schema(description = "用户类型", example = "1")
    private Integer userType;

    @Schema(description = "消息类型", example = "demo-message")
    @NotEmpty(message = "消息类型不能为空")
    private String messageType;
    @Schema(description = "消息内容", example = "{\"name\":\"李四\"}}")
    @NotEmpty(message = "消息内容不能为空")
    private String messageContent;

}
