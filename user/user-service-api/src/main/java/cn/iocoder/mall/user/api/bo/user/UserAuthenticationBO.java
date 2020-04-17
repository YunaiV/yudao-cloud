package cn.iocoder.mall.user.api.bo.user;

import cn.iocoder.mall.system.api.bo.oauth2.OAuth2AccessTokenBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("用户认证 BO")
@Data
@Accessors(chain = true)
public class UserAuthenticationBO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "昵称", required = true, example = "小王")
    private String nickname;

    private OAuth2AccessTokenBO token;

}
