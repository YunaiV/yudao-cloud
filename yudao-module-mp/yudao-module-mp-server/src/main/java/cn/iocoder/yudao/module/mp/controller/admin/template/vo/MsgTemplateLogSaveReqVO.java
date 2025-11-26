package cn.iocoder.yudao.module.mp.controller.admin.template.vo;

import java.time.LocalDateTime;

import com.alibaba.fastjson.JSON;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
/**
 * @author dengsl
 */
@Schema(description = "管理后台 - 微信模版消息发送记录新增/修改 Request VO")
@Data
public class MsgTemplateLogSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22254")
    private Long id;

    @Schema(description = "appId", requiredMode = Schema.RequiredMode.REQUIRED, example = "6914")
    @NotEmpty(message = "appId不能为空")
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
    private LocalDateTime sendTime;

    @Schema(description = "发送状态 0成功，1失败", example = "2")
    private Integer sendStatus;

    @Schema(description = "发送结果")
    private String sendResult;

    public MsgTemplateLogSaveReqVO(WxMpTemplateMessage msg, String appid, Integer sendStatus, String sendResult) {
        this.appId = appid;
        this.toUser = msg.getToUser();
        this.templateId = msg.getTemplateId();
        this.url = msg.getUrl();
        if (ObjectUtil.isNotEmpty(msg.getMiniProgram())) {
            this.miniProgramAppId = msg.getMiniProgram().getAppid();
            this.miniProgramPagePath = msg.getMiniProgram().getPagePath();
        }
        this.sendStatus = sendStatus;
        this.data = JSON.toJSONString(msg.getData());
        this.sendTime = LocalDateTime.now();
        this.sendResult = sendResult;
    }

}