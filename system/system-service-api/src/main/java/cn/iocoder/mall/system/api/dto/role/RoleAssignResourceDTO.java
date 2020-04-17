package cn.iocoder.mall.system.api.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Set;

@ApiModel("角色分配资源 DTO")
@Data
@Accessors(chain = true)
public class RoleAssignResourceDTO {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    @NotNull(message = "角色编号不能为空")
    private Integer id;

    @ApiModelProperty(value = "资源编号数组", example = "1,2")
    private Set<Integer> resourceIds;

}
