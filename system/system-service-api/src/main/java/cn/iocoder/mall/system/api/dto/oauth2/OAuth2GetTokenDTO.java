package cn.iocoder.mall.system.api.dto.oauth2;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.system.api.constant.ResourceTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("OAuth2 身份验证 DTO")
@Data
@Accessors(chain = true)
public class OAuth2GetTokenDTO implements Serializable {

    @ApiModelProperty(value = "accessToken", required = true, example = "001e8f49b20e47f7b3a2de774497cd50")
    @NotEmpty(message = "accessToken 不能为空")
    private String accessToken;

    @ApiModelProperty(value = "用户类型", required = true, example = "1", notes = "参见 ResourceTypeEnum 枚举")
    @NotNull(message = "用户类型不能为空")
    @InEnum(value = ResourceTypeEnum.class, message = "用户类型必须是 {value}")
    private Integer userType;

}
