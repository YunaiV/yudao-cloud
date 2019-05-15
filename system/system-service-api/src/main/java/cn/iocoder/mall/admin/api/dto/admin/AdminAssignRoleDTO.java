package cn.iocoder.mall.admin.api.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Set;

@ApiModel("管理员分配角色 DTO")
@Data
@Accessors(chain = true)
public class AdminAssignRoleDTO {

    @ApiModelProperty(value = "管理员编号", required = true, example = "1")
    @NotNull(message = "管理员编号不能为空")
    private Integer id;

    @ApiModelProperty(value = "角色编号数组", example = "1")
    private Set<Integer> roleIds;

}
