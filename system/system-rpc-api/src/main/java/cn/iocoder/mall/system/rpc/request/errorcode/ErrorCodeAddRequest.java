package cn.iocoder.mall.system.rpc.request.errorcode;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 错误码模块，枚举初始化错误码时使用
 * @author ding
 */
@Data
@Accessors(chain = true)
public class ErrorCodeAddRequest implements Serializable {
    @ApiModelProperty(value = "错误码信息", required = true)
    @NotEmpty(message = "错误码信息不能为空")
    private String message;

    @ApiModelProperty(value = "错误码编码")
    @NotEmpty(message = "错误码编码不能为空")
    private Integer code;

    @ApiModelProperty(value = "错误码分组，字典表获取")
    @NotEmpty(message = "错误码分组不能为空")
    private Integer group;

    @ApiModelProperty(value = "错误码角色，系统内置（枚举）还是自定义")
    @NotEmpty(message = "错误码角色不能空")
    private Integer type;
}
