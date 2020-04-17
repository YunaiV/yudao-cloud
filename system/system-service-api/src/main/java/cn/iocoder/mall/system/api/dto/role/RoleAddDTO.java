package cn.iocoder.mall.system.api.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ApiModel("角色添加 DTO")
@Data
@Accessors(chain = true)
public class RoleAddDTO implements Serializable {

    @ApiModelProperty(name = "name", value = "角色名字（标识）", required = true, example = "系统管理员")
    @NotEmpty(message = "角色名字不能为空")
    private String name;

}
