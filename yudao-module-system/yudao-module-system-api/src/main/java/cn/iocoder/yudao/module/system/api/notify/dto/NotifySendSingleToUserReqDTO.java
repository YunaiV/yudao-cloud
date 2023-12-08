package cn.iocoder.yudao.module.system.api.notify.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

@Schema(description = "RPC 服务 - 站内信发送给 Admin 或者 Member 用户 Request DTO")
@Data
public class NotifySendSingleToUserReqDTO {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @Schema(description = "站内信模板编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "USER_SEND")
    @NotEmpty(message = "站内信模板编号不能为空")
    private String templateCode;
    @Schema(description = "邮件模板参数")
    private Map<String, Object> templateParams;
}
