package cn.iocoder.mall.admin.api.bo.admin;

import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AccessTokenBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("管理员认证 BO")
@Data
@Accessors(chain = true)
public class AdminAuthenticationBO {

    @ApiModelProperty(value = "管理员编号", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "昵称", required = true, example = "小王")
    private String nickname;

    private OAuth2AccessTokenBO token;

}
