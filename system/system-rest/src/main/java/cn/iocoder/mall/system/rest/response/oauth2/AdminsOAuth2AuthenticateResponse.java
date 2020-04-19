package cn.iocoder.mall.system.rest.response.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("管理员 - OAuth2 模块 - 认证响应")
@Data
@Accessors(chain = true)
public class AdminsOAuth2AuthenticateResponse {

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
    public static class Admin {

        @ApiModelProperty(value = "管理员编号", required = true, example = "1")
        private Integer id;

        @ApiModelProperty(value = "真实名字", required = true, example = "小王")
        private String name;

    }

    /**
     * TODO 晚点测试下 swagger 的表现
     */
    private Admin admin;
    /**
     * TODO 晚点测试下 swagger 的表现
     */
    private Token token;

}
