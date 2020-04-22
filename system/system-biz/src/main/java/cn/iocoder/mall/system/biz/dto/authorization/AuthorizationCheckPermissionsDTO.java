package cn.iocoder.mall.system.biz.dto.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * OAuth2 模块 - 访问令牌认证 Request
 */
@Data
@Accessors(chain = true)
public class AuthorizationCheckPermissionsDTO {

    @NotNull(message = "访问令牌不能为空")
    private String accessToken;
    @NotNull(message = "IP 不能为空")
    private String ip;

}
