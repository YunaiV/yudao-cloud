package cn.iocoder.mall.system.biz.dto.oatuh2;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

// TODO 注释
@Data
@Accessors(chain = true)
public class OAuth2AccessTokenAuthenticateDTO {

    @NotNull(message = "访问令牌不能为空")
    private String accessToken;
    @NotNull(message = "IP 不能为空")
    private String ip;

}
