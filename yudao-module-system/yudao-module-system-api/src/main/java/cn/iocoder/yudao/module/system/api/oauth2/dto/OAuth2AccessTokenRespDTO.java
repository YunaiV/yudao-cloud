package cn.iocoder.yudao.module.system.api.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@ApiModel("RPC 服务 - OAuth2 访问令牌的信息 Response DTO")
@Data
@Accessors(chain = true)
public class OAuth2AccessTokenRespDTO implements Serializable {

    @ApiModelProperty(value = "访问令牌", required = true, example = "tudou")
    private String accessToken;

    @ApiModelProperty(value = "刷新令牌", required = true, example = "haha")
    private String refreshToken;

    @ApiModelProperty(value = "用户编号", required = true, example = "10")
    private Long userId;

    @ApiModelProperty(value = "用户类型", required = true, example = "1", notes = "参见 UserTypeEnum 枚举")
    private Integer userType;

    @ApiModelProperty(value = "过期时间", required = true)
    private Date expiresTime;

}
