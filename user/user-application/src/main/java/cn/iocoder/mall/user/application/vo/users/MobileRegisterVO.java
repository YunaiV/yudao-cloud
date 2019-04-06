package cn.iocoder.mall.user.application.vo.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("手机注册结果 VO")
@Data
@Accessors(chain = true)
public class MobileRegisterVO {

    @ApiModelProperty(value = "访问令牌", required = true, example = "2e3d7635c15e47e997611707a237859f")
    private String accessToken;
    @ApiModelProperty(value = "刷新令牌", required = true, example = "d091e7c35bbb4313b0f557a6ef23d033")
    private String refreshToken;
    @ApiModelProperty(value = "过期时间，单位：秒", required = true, example = "2879")
    private Integer expiresIn;

}
