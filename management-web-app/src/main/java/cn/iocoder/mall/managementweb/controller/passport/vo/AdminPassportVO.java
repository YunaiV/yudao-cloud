package cn.iocoder.mall.managementweb.controller.passport.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("管理员通信证信息 VO")
@Data
@Accessors(chain = true)
public class AdminPassportVO {

    @ApiModel("认证信息")
    @Data
    @Accessors(chain = true)
    public static class Authentication {

        @ApiModelProperty(value = "访问令牌", required = true, example = "001e8f49b20e47f7b3a2de774497cd50")
        private String accessToken;
        @ApiModelProperty(value = "刷新令牌", required = true, example = "001e8f49b20e47f7b3a2de774497cd50")
        private String refreshToken;
        @ApiModelProperty(value = "过期时间", required = true)
        private Date expiresTime;

    }

    @ApiModel("管理员信息")
    @Data
    @Accessors(chain = true)
    public static class Admin {

        @ApiModelProperty(value = "用户编号", required = true, example = "1")
        private Integer id;
        @ApiModelProperty(value = "真实姓名", required = true, example = "小王")
        private String name;

    }

    /**
     * 管理员信息
     */
    private Admin admin;
    /**
     * 认证信息
     */
    private Authentication authorization;

}
