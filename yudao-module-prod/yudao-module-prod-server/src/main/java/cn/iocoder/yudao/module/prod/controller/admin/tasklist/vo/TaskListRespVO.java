package cn.iocoder.yudao.module.prod.controller.admin.tasklist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 任务列 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TaskListRespVO {

    @Schema(description = "任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14040")
    @ExcelProperty("任务id")
    private Long id;

    @Schema(description = "任务类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty(value = "任务类型", converter = DictConvert.class)
    @DictFormat("prod_task_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Boolean taskType;

    @Schema(description = "产品模板配置id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9853")
    @ExcelProperty("产品模板配置id")
    private Long prodConfigId;

    @Schema(description = "生成数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生成数量")
    private Integer taskGenQty;

    @Schema(description = "每多少次切换prompt")
    @ExcelProperty("每多少次切换prompt")
    private Integer taskSwitchNum;

    @Schema(description = "任务咒语")
    @ExcelProperty("任务咒语")
    private String taskPrompt;

    @Schema(description = "图片大小")
    @ExcelProperty("图片大小")
    private String imgSize;

    @Schema(description = "模板名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "2308")
    @ExcelProperty("模板名称")
    private Long templateId;

    @Schema(description = "任务咒语描述")
    @ExcelProperty("任务咒语描述")
    private String taskPromptDesc;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "插入点列表")
    private List<InsertPointRespVO> insertPoints;
    
    @Schema(description = "插入点响应VO")
    @Data
    public static class InsertPointRespVO {
        
        @Schema(description = "插入点ID")
        private Long id;
        
        @Schema(description = "插入点")
        private String point;
        
        @Schema(description = "替换的内容")
        private String content;
    }

}