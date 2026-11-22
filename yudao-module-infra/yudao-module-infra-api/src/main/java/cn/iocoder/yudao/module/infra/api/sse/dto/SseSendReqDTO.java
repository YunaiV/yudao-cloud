package cn.iocoder.yudao.module.infra.api.sse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "RPC 服务 - SSE 消息发送 Request DTO")
@Data
public class SseSendReqDTO {

    @Schema(description = "SSE 连接编号，以 sse: 开头", example = "sse:abc")
    private String connectionId;
    @Schema(description = "用户编号", example = "1024")
    private Long userId;
    @Schema(description = "用户类型", example = "2")
    private Integer userType;

    @Schema(description = "消息类型，作为 SSE 的 event 名", example = "notice-push")
    @NotEmpty(message = "消息类型不能为空")
    private String messageType;
    @Schema(description = "消息内容", example = "{\"name\":\"李四\"}")
    @NotEmpty(message = "消息内容不能为空")
    private String messageContent;

}
