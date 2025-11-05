package cn.iocoder.yudao.module.mp.controller.admin.template.vo;

import java.time.LocalDateTime;

import cn.idev.excel.annotation.ExcelIgnore;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * @author dengsl
 */
@Schema(description = "管理后台 - 消息模板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MsgTemplateRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "7019")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "appId", requiredMode = Schema.RequiredMode.REQUIRED, example = "9758")
    @ExcelProperty("appId")
    private String appId;

    @Schema(description = "公众号模板ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14517")
    @ExcelProperty("公众号模板ID")
    private String templateId;

    @Schema(description = "模版名称", example = "赵六")
    @ExcelProperty("模版名称")
    private String name;

    @Schema(description = "标题")
    @ExcelProperty("标题")
    private String title;

    @Schema(description = "模板内容")
    @ExcelProperty("模板内容")
    private String content;

    @Schema(description = "消息内容")
    @ExcelProperty("消息内容")
    private String data;

    @Schema(description = "链接", example = "https://www.iocoder.cn")
    @ExcelProperty("链接")
    private String url;

    @Schema(description = "小程序appId")
    @ExcelProperty("小程序appId")
    private String miniProgramAppId;

    @Schema(description = "小程序页面路径")
    @ExcelProperty("小程序页面路径")
    private String miniProgramPagePath;

    @Schema(description = "是否有效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("是否有效")
    private Integer status;

    @Schema(description = "公众号是否已移除 0未移除,1已移除", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("公众号是否已移除")
    private Integer isRemoved;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "模板消息配置id")
    @ExcelIgnore
    private Long configId;

    @Schema(description = "模板类型")
    @ExcelIgnore
    private String templateType;

}