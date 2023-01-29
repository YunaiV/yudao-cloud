package cn.iocoder.yudao.module.system.api.notify.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@ApiModel("RPC 服务 - 站内信发送给 Admin 或者 Member 用户 Request DTO")
@Data
public class NotifySendSingleToUserReqDTO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @ApiModelProperty(value = "站内信模板编号", required = true, example = "USER_SEND")
    @NotEmpty(message = "站内信模板编号不能为空")
    private String templateCode;
    @ApiModelProperty(value = "邮件模板参数")
    private Map<String, Object> templateParams;
}
