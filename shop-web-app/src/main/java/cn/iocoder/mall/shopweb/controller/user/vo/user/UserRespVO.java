package cn.iocoder.mall.shopweb.controller.user.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("用户信息 VO")
@Data
@Accessors(chain = true)
public class UserRespVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "123")
    private Integer id;
    @ApiModelProperty(value = "手机号", required = true, example = "15601691300")
    private String mobile;
    @ApiModelProperty(value = "昵称", required = true, example = "小王")
    private String nickname;
    @ApiModelProperty(value = "头像", required = true, example = "http://www.iocoder.cn/xxx.jpg")
    private String avatar;

}
