package cn.iocoder.mall.system.rest.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/12
 * @Description: 管理员 - 用户信息 - 更新用户信息
 */
@ApiModel("更新用户信息Request")
@Data
@Accessors(chain = true)
public class AdminsUserUpdateRequest {

    @ApiModelProperty(name = "id", value = "用户编号", required = true, example = "1")
    @NotNull(message = "用户编号不能为空")
    private Integer id;

    @ApiModelProperty(name = "nickname", value = "昵称", required = true, example = "小王")
    private String nickname;

    @ApiModelProperty(name = "avatar", value = "头像", required = true, example = "http://www.iocoder.cn/xxx.jpg")
    private String avatar;
}
