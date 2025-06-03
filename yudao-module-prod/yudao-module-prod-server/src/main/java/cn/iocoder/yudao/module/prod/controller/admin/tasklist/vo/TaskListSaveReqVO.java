package cn.iocoder.yudao.module.prod.controller.admin.tasklist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

@Schema(description = "管理后台 - 任务列新增/修改 Request VO")
@Data
public class TaskListSaveReqVO {

    @Schema(description = "任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14040")
    private Long id;

    @Schema(description = "任务类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "任务类型不能为空")
    private Boolean taskType;

    @Schema(description = "产品模板配置id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9853")
    @NotNull(message = "产品模板配置id不能为空")
    private Long prodConfigId;

    @Schema(description = "生成数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生成数量不能为空")
    private Integer taskGenQty;

    @Schema(description = "每多少次切换prompt")
    private Integer taskSwitchNum;

    @Schema(description = "任务咒语")
    private String taskPrompt;

    @Schema(description = "图片大小")
    private String imgSize;

    @Schema(description = "模板名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "2308")
    @NotNull(message = "模板名称不能为空")
    private Long templateId;

    @Schema(description = "任务咒语描述")
    private String taskPromptDesc;
    
    @Schema(description = "插入点列表")
    @Valid
    private List<InsertPointReqVO> insertPoints;
    
    @Schema(description = "插入点请求VO")
    @Data
    public static class InsertPointReqVO {
        
        @Schema(description = "插入点", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "插入点不能为空")
        private String point;
        
        @Schema(description = "替换的内容", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "替换的内容不能为空")
        private String content;
    }

}