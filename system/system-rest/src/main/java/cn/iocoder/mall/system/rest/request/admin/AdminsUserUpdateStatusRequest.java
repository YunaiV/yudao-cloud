package cn.iocoder.mall.system.rest.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/12
 * @Description: 管理员 - 用户信息 - 更新用户状态
 */
@ApiModel("更新用户状态Request")
@Data
@Accessors(chain = true)
public class AdminsUserUpdateStatusRequest {

    @ApiModelProperty(name = "id", value = "用户编号", required = true, example = "1")
    @NotNull(message = "用户编号不能为空")
    private Integer id;

    @ApiModelProperty(name = "status", value = "用户状态。1 - 开启；2 - 禁用", required = true, example = "1")
    @NotNull(message = "用户状态不能为空")
    private Integer status;
}
