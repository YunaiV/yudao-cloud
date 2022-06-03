package cn.iocoder.yudao.module.system.api.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel("RPC 服务 - OAuth2.0 访问令牌的校验 Response DTO")
@Data
public class OAuth2AccessTokenCheckRespDTO implements Serializable {

    @ApiModelProperty(value = "用户编号", required = true, example = "10")
    private Long userId;

    @ApiModelProperty(value = "用户类型", required = true, example = "1", notes = "参见 UserTypeEnum 枚举")
    private Integer userType;

    @ApiModelProperty(value = "租户编号", required = true, example = "1024")
    private Long tenantId;

    @ApiModelProperty(value = "授权范围的数组", example = "user_info")
    private List<String> scopes;

}
