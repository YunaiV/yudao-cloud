package cn.iocoder.mall.system.application.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("管理员拥有的角色 VO")
@Data
@Accessors(chain = true)
public class AdminRoleVO {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "角色名字", required = true, example = "系统管理员")
    private String name;
    @ApiModelProperty(value = "是否授权", required = true, example = "true")
    private Boolean assigned;

}
