package cn.iocoder.mall.system.rest.request.authorization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("管理员 - 角色模块 - 修改角色 Request")
@Data
@Accessors(chain = true)
public class RoleUpdateDTO {

    @ApiModelProperty(value = "角色编号", required = true, example = "123")
    @NotNull(message = "角色编号不能为空")
    private Integer id;

    @ApiModelProperty(value = "角色名字", required = true, example = "系统管理员")
    @NotEmpty(message = "角色名字不能为空")
    private String name;

    @ApiModelProperty(value = "角色编码", example = "SUPER_ADMIN")
    private String code;

}
