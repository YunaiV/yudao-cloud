package cn.iocoder.mall.system.rest.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/12
 * @Description: 管理员 - 用户信息 - 用户分页列表Response
 */
@ApiModel("用户分页信息 Response")
@Data
@Accessors(chain = true)
public class AdminsUserPageResponse {

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "昵称", required = false, example = "1")
    private String nickname;

    @ApiModelProperty(value = "手机号", required = true, example = "13631780241")
    private String mobile;

    @ApiModelProperty(value = "头像", required = false, example = "http://www.iocoder.cn/xxx.jpg")
    private String avatar;

    @ApiModelProperty(value = "用户状态 1 - 开启；2 - 禁用", required = true, example = "1")
    private Integer status;
}
