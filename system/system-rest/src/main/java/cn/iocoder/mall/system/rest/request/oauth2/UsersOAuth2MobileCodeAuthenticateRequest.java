package cn.iocoder.mall.system.rest.request.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@ApiModel("用户 - OAuth2 模块 - 手机验证码认证请求")
@Data
@Accessors(chain = true)
public class UsersOAuth2MobileCodeAuthenticateRequest {

    @ApiModelProperty(value = "手机号", required = true, example = "15601691300")
    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "账号长度为 11 位")
    @Pattern(regexp = "^[0-9]+$", message = "手机号必须都是数字")
    private String mobile;

    @ApiModelProperty(value = "手机验证码", required = true, example = "1024")
    @NotEmpty(message = "手机验证码不能为空")
    @Length(min = 4, max = 6, message = "手机验证码长度为 4-6 位")
    @Pattern(regexp = "^[0-9]+$", message = "手机验证码必须都是数字")
    private String code;

}
