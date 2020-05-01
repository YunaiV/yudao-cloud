package cn.iocoder.mall.system.biz.dto.oatuh2;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 认证模块 - 账号 + 密码认证 DTO
 */
@Data
@Accessors(chain = true)
public class OAuth2UsernameAuthenticateDTO {

    @NotEmpty(message = "账号不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;

}
