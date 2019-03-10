package cn.iocoder.mall.user.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户信息 VO")
public class UsersUserVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "123")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public UsersUserVO setId(Integer id) {
        this.id = id;
        return this;
    }

}