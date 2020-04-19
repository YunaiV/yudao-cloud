package cn.iocoder.mall.system.rest.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("用户 - OAuth2 模块 - 认证响应")
@Data
@Accessors(chain = true)
public class UsersOAuth2AuthenticateResponse {

    @Data
    public static class Token {

        @ApiModelProperty(value = "access token", required = true, example = "001e8f49b20e47f7b3a2de774497cd50")
        private String accessToken;

        @ApiModelProperty(value = "refresh token", required = true, example = "001e8f49b20e47f7b3a2de774497cd50")
        private String refreshToken;

        @ApiModelProperty(value = "过期时间", required = true)
        private Date expiresTime;

    }

    @Data
    public static class User {

        @ApiModelProperty(value = "管理员编号", required = true, example = "1")
        private Integer id;

        @ApiModelProperty(value = "昵称", required = true, example = "小王")
        private String nickname;

    }

    /**
     * TODO 晚点测试下 swagger 的表现
     */
    private User user;

    /**
     * TODO 晚点测试下 swagger 的表现
     */
    private Token token;

}
