package cn.iocoder.yudao.module.system.api.social.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "RPC 服务 - 微信公众号 JSAPI 签名 Response DTO")
@Data
public class SocialWxJsapiSignatureRespDTO {

    @Schema(description = "微信公众号的 appId", requiredMode = Schema.RequiredMode.REQUIRED, example = "wx123456")
    private String appId;

    @Schema(description = "匿名串", requiredMode = Schema.RequiredMode.REQUIRED, example = "zsw")
    private String nonceStr;

    @Schema(description = "时间戳", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456789")
    private Long timestamp;

    @Schema(description = "URL", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    private String url;

    @Schema(description = "签名", requiredMode = Schema.RequiredMode.REQUIRED, example = "zsw")
    private String signature;

}
