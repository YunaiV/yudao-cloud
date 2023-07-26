package cn.iocoder.yudao.module.system.api.oauth2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Schema(description = "RPC 服务 - OAuth2 访问令牌的校验 Response DTO")
@Data
public class OAuth2AccessTokenCheckRespDTO implements Serializable {

    @Schema(description = "用户编号", required = true, example = "10")
    private Long userId;

    @Schema(description = "用户类型，参见 UserTypeEnum 枚举", required = true, example = "1")
    private Integer userType;

    @Schema(description = "租户编号", required = true, example = "1024")
    private Long tenantId;

    @Schema(description = "授权范围的数组", example = "user_info")
    private List<String> scopes;

}
