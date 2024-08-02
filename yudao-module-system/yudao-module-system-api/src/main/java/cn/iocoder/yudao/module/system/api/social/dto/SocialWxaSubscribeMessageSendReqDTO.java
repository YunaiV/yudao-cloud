package cn.iocoder.yudao.module.system.api.social.dto;

import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Schema(description = "RPC 服务 - 微信小程序订阅消息发送 Request DTO")
@Data
public class SocialWxaSubscribeMessageSendReqDTO {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户编号不能为空")
    private Long userId;
    @Schema(description = "用户类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @InEnum(UserTypeEnum.class)
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    @Schema(description = "消息模版标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "模版标题")
    @NotEmpty(message = "消息模版标题不能为空")
    private String templateTitle;

    @Schema(description = "点击模板卡片后的跳转页面，仅限本小程序内的页面", example = "pages/index?foo=bar")
    private String page; // 支持带参数，（示例 index?foo=bar ）。该字段不填则模板无跳转。

    @Schema(description = "模板内容的参数")
    private Map<String, String> messages;

    public SocialWxaSubscribeMessageSendReqDTO addMessage(String key, String value) {
        if (messages == null) {
            messages = new HashMap<>();
        }
        messages.put(key, value);
        return this;
    }

}
