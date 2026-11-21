package cn.iocoder.yudao.module.ai.controller.admin.mindmap.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - AI 思维导图生成 Request VO")
@Data
public class AiMindMapGenerateReqVO {

    @Schema(description = "思维导图内容提示", requiredMode = Schema.RequiredMode.REQUIRED, example = "Java 学习路线")
    @NotBlank(message = "思维导图内容提示不能为空")
    private String prompt;

    @Schema(description = "模型编号，指定时必须为启用的对话模型；未指定时沿用角色或默认模型", example = "1")
    private Long modelId;

}
