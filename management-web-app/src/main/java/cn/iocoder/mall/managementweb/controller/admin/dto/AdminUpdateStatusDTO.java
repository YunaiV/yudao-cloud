package cn.iocoder.mall.managementweb.controller.admin.dto;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@ApiModel("管理员更新状态 DTO")
@Data
@Accessors(chain = true)
public class AdminUpdateStatusDTO {

    @ApiModelProperty(value = "管理员编号", required = true, example = "1")
    @NotNull(message = "管理员编号不能为空")
    private Integer adminId;

    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
