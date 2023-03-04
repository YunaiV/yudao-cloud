package cn.iocoder.yudao.module.system.api.oauth2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "RPC 服务 - OAuth2 访问令牌的信息 Response DTO")
@Data
@Accessors(chain = true)
public class OAuth2AccessTokenRespDTO implements Serializable {

    @Schema(description = "访问令牌", required = true, example = "tudou")
    private String accessToken;

    @Schema(description = "刷新令牌", required = true, example = "haha")
    private String refreshToken;

    @Schema(description = "用户编号", required = true, example = "10")
    private Long userId;

    @Schema(description = "用户类型,参见 UserTypeEnum 枚举", required = true, example = "1" )
    private Integer userType;

    @Schema(description = "过期时间", required = true)
    private LocalDateTime expiresTime;

}