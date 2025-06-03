package cn.iocoder.yudao.module.prod.controller.admin.tasklist.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 任务列分页 Request VO")
@Data
public class TaskListPageReqVO extends PageParam {

    @Schema(description = "任务类型", example = "2")
    private Boolean taskType;

    @Schema(description = "产品模板配置id", example = "9853")
    private Long prodConfigId;

    @Schema(description = "生成数量")
    private Integer taskGenQty;

    @Schema(description = "每多少次切换prompt")
    private Integer taskSwitchNum;

    @Schema(description = "任务咒语")
    private String taskPrompt;

    @Schema(description = "图片大小")
    private String imgSize;

    @Schema(description = "模板名称", example = "2308")
    private Long templateId;

    @Schema(description = "任务咒语描述")
    private String taskPromptDesc;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}