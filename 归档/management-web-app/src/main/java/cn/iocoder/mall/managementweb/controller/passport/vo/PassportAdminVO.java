package cn.iocoder.mall.managementweb.controller.passport.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("管理员信息 VO")
@Data
@Accessors(chain = true)
public class PassportAdminVO {

    @ApiModelProperty(value = "真实名字", required = true, example = "小王")
    private String name;
    @ApiModelProperty(value = "头像", required = true, example = "http://www.iocoder.cn/xxx.jpg")
    private String avatar;

}
