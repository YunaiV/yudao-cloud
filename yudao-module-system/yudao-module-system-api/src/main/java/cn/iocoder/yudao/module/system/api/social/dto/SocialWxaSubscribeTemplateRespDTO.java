package cn.iocoder.yudao.module.system.api.social.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "RPC 服务 - 小程序订阅消息模版 Response DTO")
@Data
public class SocialWxaSubscribeTemplateRespDTO {

    @Schema(description = "模版编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String id;

    @Schema(description = "模版标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "模版标题")
    private String title;

    @Schema(description = "模版内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "模版内容")
    private String content;

    @Schema(description = "模板内容示例", requiredMode = Schema.RequiredMode.REQUIRED, example = "模版内容示例")
    private String example;

    @Schema(description = "模版类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Integer type; // 2：为一次性订阅；3：为长期订阅

}
