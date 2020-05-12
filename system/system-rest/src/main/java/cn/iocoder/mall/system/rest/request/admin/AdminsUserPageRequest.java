package cn.iocoder.mall.system.rest.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/12
 * @Description: 管理员 - 用户信息 - 用户分页列表
 */
@ApiModel("用户分页列表Request")
@Data
@Accessors(chain = true)
public class AdminsUserPageRequest {

    @ApiModelProperty(name = "nickname", value = "昵称，模糊匹配", example = "小王")
    private String nickname;

    @ApiModelProperty(name = "status", value = "状态。1 - 开启；2 - 禁用", example = "0")
    private Integer status;

    @ApiModelProperty(name = "pageNo", value = "页码，从 1 开始", example = "1")
    private Integer pageNo = 1;

    @ApiModelProperty(name = "pageSize", value = "每页条数", required = true, example = "10")
    private Integer pageSize = 10;
}
