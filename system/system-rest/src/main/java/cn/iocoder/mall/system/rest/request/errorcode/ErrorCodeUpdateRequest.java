package cn.iocoder.mall.system.rest.request.errorcode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 类 描 述：错误码模块
 * @author ding
 */
@ApiModel("管理员 - 错误码模块 - 修改错误码 Request")
@Data
@Accessors(chain = true)
public class ErrorCodeUpdateRequest {
    @ApiModelProperty(value = "错误码编号", required = true, example = "1")
    @NotNull(message = "错误码不能为空")
    private Integer id;

    @ApiModelProperty(value = "错误码信息", required = true, example = "系统管理员")
    @NotEmpty(message = "错误码信息不能为空")
    private String message;

    @ApiModelProperty(value = "错误码编码", example = "SUPER_ADMIN")
    @NotEmpty(message = "错误码编码不能为空")
    private Integer code;
}
