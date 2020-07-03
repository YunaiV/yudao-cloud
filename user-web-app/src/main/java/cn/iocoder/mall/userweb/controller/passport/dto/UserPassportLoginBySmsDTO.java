package cn.iocoder.mall.userweb.controller.passport.dto;

import cn.iocoder.common.framework.validator.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("用户短信验证码登陆 DTO")
@Data
@Accessors(chain = true)
public class UserPassportLoginBySmsDTO implements Serializable {

    @ApiModelProperty(value = "手机号", example = "15601691234")
    @Mobile
    private String mobile;
    @ApiModelProperty(value = "验证码", example = "1234")
    @NotNull(message = "验证码不能为空")
    private String code;

}
