package cn.iocoder.yudao.module.mp.controller.admin.template.vo;

import java.time.LocalDateTime;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author dengsl
 */
@Schema(description = "管理后台 - 微信模版消息发送记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MsgTemplateLogRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22254")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "appId", requiredMode = Schema.RequiredMode.REQUIRED, example = "6914")
    @ExcelProperty("appId")
    private String appId;

    @Schema(description = "用户openid")
    @ExcelProperty("用户openid")
    private String toUser;

    @Schema(description = "公众号模板ID", example = "8374")
    @ExcelProperty("公众号模板ID")
    private String templateId;

    @Schema(description = "消息内容")
    @ExcelProperty("消息内容")
    private String data;

    @Schema(description = "链接", example = "https://www.iocoder.cn")
    @ExcelProperty("链接")
    private String url;

    @Schema(description = "小程序appid", example = "21567")
    @ExcelProperty("小程序appid")
    private String miniProgramAppId;

    @Schema(description = "小程序页面路径")
    @ExcelProperty("小程序页面路径")
    private String miniProgramPagePath;

    @Schema(description = "发送时间")
    @ExcelProperty("发送时间")
    private LocalDateTime sendTime;

    @Schema(description = "发送状态 0成功，1失败", example = "2")
    @ExcelProperty("发送状态 0成功，1失败")
    private String sendStatus;

    @Schema(description = "发送结果")
    @ExcelProperty("发送结果")
    private String sendResult;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}