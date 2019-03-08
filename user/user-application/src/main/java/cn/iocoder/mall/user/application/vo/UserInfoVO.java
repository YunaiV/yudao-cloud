package cn.iocoder.mall.user.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户信息 VO")
public class UserInfoVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "123")
    private Long id;

    public Long getId() {
        return id;
    }

    public UserInfoVO setId(Long id) {
        this.id = id;
        return this;
    }

}