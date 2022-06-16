package cn.iocoder.mall.managementweb.controller.passport.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("访问令牌信息 VO")
@Data
@Accessors(chain = true)
public class PassportAccessTokenVO {

    @ApiModelProperty(value = "访问令牌", required = true, example = "001e8f49b20e47f7b3a2de774497cd50")
    private String accessToken;
    @ApiModelProperty(value = "刷新令牌", required = true, example = "001e8f49b20e47f7b3a2de774497cd50")
    private String refreshToken;
    @ApiModelProperty(value = "过期时间", required = true)
    private Date expiresTime;

}
