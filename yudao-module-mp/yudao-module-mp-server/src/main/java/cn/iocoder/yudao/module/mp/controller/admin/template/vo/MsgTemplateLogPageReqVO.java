package cn.iocoder.yudao.module.mp.controller.admin.template.vo;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author dengsl
 */
@Schema(description = "管理后台 - 微信模版消息发送记录分页 Request VO")
@Data
public class MsgTemplateLogPageReqVO extends PageParam {

    @Schema(description = "appId", example = "6914")
    private String appId;

    @Schema(description = "用户openid")
    private String toUser;

    @Schema(description = "公众号模板ID", example = "8374")
    private String templateId;

    @Schema(description = "消息内容")
    private String data;

    @Schema(description = "链接", example = "https://www.iocoder.cn")
    private String url;

    @Schema(description = "小程序appid", example = "21567")
    private String miniProgramAppId;

    @Schema(description = "小程序页面路径")
    private String miniProgramPagePath;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] sendTime;

    @Schema(description = "发送状态 0成功，1失败", example = "2")
    private Integer sendStatus;

    @Schema(description = "发送结果")
    private String sendResult;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}