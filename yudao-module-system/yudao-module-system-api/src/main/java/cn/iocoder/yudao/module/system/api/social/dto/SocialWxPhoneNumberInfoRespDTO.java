package cn.iocoder.yudao.module.system.api.social.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "RPC 服务 - 微信小程序的手机信息 Response DTO")
@Data
public class SocialWxPhoneNumberInfoRespDTO {

    @Schema(description = "用户绑定的手机号（国外手机号会有区号）", requiredMode = Schema.RequiredMode.REQUIRED, example = "021-13579246810")
    private String phoneNumber;

    @Schema(description = "没有区号的手机号", requiredMode = Schema.RequiredMode.REQUIRED, example = "13579246810")
    private String purePhoneNumber;
    @Schema(description = "区号", requiredMode = Schema.RequiredMode.REQUIRED, example = "021")
    private String countryCode;

}
