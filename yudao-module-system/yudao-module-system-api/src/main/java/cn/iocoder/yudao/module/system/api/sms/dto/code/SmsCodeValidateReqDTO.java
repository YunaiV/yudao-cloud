package cn.iocoder.yudao.module.system.api.sms.dto.code;

import cn.iocoder.yudao.framework.common.validation.InEnum;
import cn.iocoder.yudao.framework.common.validation.Mobile;
import cn.iocoder.yudao.module.system.enums.sms.SmsSceneEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("RPC 服务 - 短信验证码的校验 Request DTO")
@Data
public class SmsCodeValidateReqDTO {

    @ApiModelProperty(value = "手机号", required = true, example = "15601691300")
    @Mobile
    @NotEmpty(message = "手机号不能为空")
    private String mobile;
    @ApiModelProperty(value = "发送场景", required = true, example = "1")
    @NotNull(message = "发送场景不能为空")
    @InEnum(SmsSceneEnum.class)
    private Integer scene;
    @ApiModelProperty(value = "验证码", required = true, example = "1024")
    @NotEmpty(message = "验证码")
    private String code;

}
