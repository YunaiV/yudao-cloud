package cn.iocoder.mall.system.api.bo.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel("OAUTH2 认证 BO")
@Data
@Accessors(chain = true)
public class OAuth2AuthenticationBO implements Serializable {

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    private Integer userId;

    @ApiModelProperty(value = "用户类型", required = true, example = "1", notes = "参考 UserTypeEnum 枚举")
    private Integer userType;

}
