package cn.iocoder.mall.system.rest.request.authorization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Set;

@ApiModel(value = "管理员 - 授权模块 - 分配角色资源 Request")
@Data
@Accessors(chain = true)
public class AdminsAuthorizationAssignRoleResourceRequest {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    @NotNull(message = "角色编号不能为空")
    private Integer roleId;

    @ApiModelProperty(value = "资源编号数组", example = "1,2")
    private Set<Integer> resourceIds;

}
