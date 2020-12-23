package cn.iocoder.mall.managementweb.controller.user.vo;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("用户更新状态 Request VO")
@Data
public class UserUpdateStatusReqVO {

    @ApiModelProperty(value = "用户编号", required = true)
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
