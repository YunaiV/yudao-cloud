package cn.iocoder.mall.system.rest.request.errorcode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@ApiModel("管理员 - 错误码模块 - 添加错误码 Request")
@Data
@Accessors(chain = true)
public class ErrorCodeAddRequest {

    @ApiModelProperty(value = "错误码信息", required = true, example = "系统管理员")
    @NotEmpty(message = "错误码信息不能为空")
    private String message;

    @ApiModelProperty(value = "错误码编码", example = "SUPER_ADMIN")
    @NotEmpty(message = "错误码编码不能为空")
    private Integer code;

}
