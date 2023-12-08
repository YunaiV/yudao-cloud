package cn.iocoder.yudao.module.system.api.sms.dto.send;

import cn.iocoder.yudao.framework.common.validation.Mobile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import java.util.Map;

@Schema(description = "RPC 服务 - 短信发送给 Admin 或者 Member 用户 Request DTO")
@Data
public class SmsSendSingleToUserReqDTO {

    @Schema(description = "用户编号", example = "1024")
    private Long userId;
    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED, example = "15601691300")
    @Mobile
    private String mobile;

    @Schema(description = "短信模板编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "USER_SEND")
    @NotEmpty(message = "短信模板编号不能为空")
    private String templateCode;
    @Schema(description = "短信模板参数")
    private Map<String, Object> templateParams;

}
