package cn.iocoder.yudao.module.mp.controller.admin.template.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * @author dengsl
 */
@Schema(description = "管理后台 - 消息模板新增/修改 Request VO")
@Data
public class MsgTemplateSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "7019")
    private Long id;

    @Schema(description = "appId", requiredMode = Schema.RequiredMode.REQUIRED, example = "9758")
    @NotEmpty(message = "appId不能为空")
    private String appId;

    @Schema(description = "公众号模板ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14517")
    @NotEmpty(message = "公众号模板ID不能为空")
    private String templateId;

    @Schema(description = "模版名称", example = "赵六")
    private String name;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "模板内容")
    private String content;

    @Schema(description = "消息内容")
    private String data;

    @Schema(description = "链接", example = "https://www.iocoder.cn")
    private String url;

    @Schema(description = "小程序appid")
    private String miniProgramAppId;

    @Schema(description = "小程序页面路径")
    private String miniProgramPagePath;

    @Schema(description = "模板所属行业的一级行业")
    private String primaryIndustry;

    @Schema(description = "模板所属行业的二级行业")
    private String deputyIndustry;

    @Schema(description = "是否有效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "是否有效不能为空")
    private Integer status;

}