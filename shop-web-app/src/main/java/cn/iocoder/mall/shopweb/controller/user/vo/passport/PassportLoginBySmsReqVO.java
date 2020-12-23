package cn.iocoder.mall.shopweb.controller.user.vo.passport;

import cn.iocoder.common.framework.validator.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@ApiModel("用户短信验证码登陆 Request VO")
@Data
@Accessors(chain = true)
public class PassportLoginBySmsReqVO implements Serializable {

    @ApiModelProperty(value = "手机号", required = true, example = "15601691300")
    @NotEmpty(message = "手机号不能为空")
    @Mobile
    private String mobile;

    @ApiModelProperty(value = "手机验证码", required = true, example = "1024")
    @NotEmpty(message = "手机验证码不能为空")
    @Length(min = 4, max = 6, message = "手机验证码长度为 4-6 位")
    @Pattern(regexp = "^[0-9]+$", message = "手机验证码必须都是数字")
    private String code;

}
