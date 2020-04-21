package cn.iocoder.mall.system.rpc.request.oauth2;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * OAuth2 访问令牌认证 Request
 */
@Data
@Accessors(chain = true)
public class OAuth2AccessTokenAuthenticateRequest {

    @NotNull(message = "访问令牌不能为空")
    private String accessToken;
    @NotNull(message = "IP 不能为空")
    private String ip;

}
