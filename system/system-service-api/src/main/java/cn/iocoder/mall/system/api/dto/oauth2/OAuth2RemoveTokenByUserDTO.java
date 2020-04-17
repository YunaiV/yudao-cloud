package cn.iocoder.mall.system.api.dto.oauth2;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.system.api.constant.ResourceTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("OAuth2 移除 Token DTO")
@Data
@Accessors(chain = true)
public class OAuth2RemoveTokenByUserDTO implements Serializable {

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    @NotNull(message = "用户编号不能为空")
    private Integer userId;

    @ApiModelProperty(value = "用户类型", required = true, example = "1", notes = "参见 ResourceTypeEnum 枚举")
    @NotNull(message = "用户类型不能为空")
    @InEnum(value = ResourceTypeEnum.class, message = "用户类型必须是 {value}")
    private Integer userType;

}
