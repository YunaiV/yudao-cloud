package cn.iocoder.mall.managementweb.controller.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("用户更新信息 Request VO")
@Data
public class UserUpdateInfoReqVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    @NotNull(message = "用户编号不能为空")
    private Integer id;
    @ApiModelProperty(value = "昵称", example = "臭艿艿")
    private String nickname;
    @ApiModelProperty(value = "头像", example = "http://www.iocoder.cn/nainainai.jpg")
    private String avatar;
    @ApiModelProperty(value = "手机号", example = "15601691300")
    private String mobile;
    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

}
