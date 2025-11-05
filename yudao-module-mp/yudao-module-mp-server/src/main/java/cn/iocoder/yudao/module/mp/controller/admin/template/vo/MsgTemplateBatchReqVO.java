package cn.iocoder.yudao.module.mp.controller.admin.template.vo;


import cn.iocoder.yudao.module.mp.controller.admin.user.vo.MpUserPageReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author dengsl
 */
@Schema(description = "管理后台 - 消息批量推送 Request VO")
@Data
public class MsgTemplateBatchReqVO extends MpUserPageReqVO {

    @Schema(description = "appId", example = "9758")
    @NotNull(message = "appId不能为空")
    private String appId;

    @Schema(description = "公众号模板ID", example = "14517")
    @NotNull(message = "公众号模板ID不能为空")
    private String templateId;
}