package cn.iocoder.yudao.module.system.api.mail.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Map;

@ApiModel("RPC 服务 - 邮件发送给 Admin 或者 Member 用户 Request DTO")
@Data
public class MailSendSingleToUserReqDTO {

    @ApiModelProperty(value = "用户编号", example = "1024")
    private Long userId;
    @ApiModelProperty(value = "手机号", required = true, example = "15601691300")
    @Email
    private String mail;

    @ApiModelProperty(value = "邮件模板编号", required = true, example = "USER_SEND")
    @NotNull(message = "邮件模板编号不能为空")
    private String templateCode;
    @ApiModelProperty(value = "邮件模板参数")
    private Map<String, Object> templateParams;

}
