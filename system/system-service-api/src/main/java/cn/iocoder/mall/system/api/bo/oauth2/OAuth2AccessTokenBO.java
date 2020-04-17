package cn.iocoder.mall.system.api.bo.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel("OAuth2 Token 信息 BO")
@Data
@Accessors(chain = true)
public class OAuth2AccessTokenBO implements Serializable {

    @ApiModelProperty(value = "accessToken", required = true, example = "001e8f49b20e47f7b3a2de774497cd50")
    private String accessToken;

    @ApiModelProperty(value = "refreshToken", required = true, example = "001e8f49b20e47f7b3a2de774497cd50")
    private String refreshToken;

    @ApiModelProperty(value = "过期时间，单位：秒", required = true, example = "1024")
    private Integer expiresIn;

}
